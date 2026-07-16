# com-google-chat

Portable `.cljc` Google Chat client: **send-only**, via an Incoming
Webhook URL. No polling/reading counterpart -- see `src/google_chat/client.cljc`
for why (Google Chat's inbound path needs a Workspace Marketplace app
registration + Google-issued JWT verification, and its full REST send API
needs service-account JWT *signing*; both are disproportionate for a
notification channel).

## Setup

1. In Google Chat, open (or create) a space -> space settings -> **Apps &
   integrations** -> **Add webhook**. Copy the generated URL (it already
   carries `key`/`token` query params -- no separate auth header needed).
2. Configure that URL as `:webhook-url` when calling `send-message!`.

## Usage

```clojure
(require '[google-chat.client :as client])

(client/send-message!
 {:http-fn    my-http-fn      ; (fn [{:keys [url method headers body]}]) -> {:status :body}
  :json-write my-json-write   ; edn -> json string
  :json-read  my-json-read    ; json string -> edn
  :webhook-url "https://chat.googleapis.com/v1/spaces/AAAA/messages?key=...&token=..."}
 "hello from manimani")
```

## Testing

```bash
clojure -M:test
clojure -M:lint
```
