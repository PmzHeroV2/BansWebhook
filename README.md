# BansWebhook

* Sends punishment info to a discord webhook

# Commands :

* /bwh reload - Reloads Configuration (bwh.reload)

# Configuration :

```yaml
reload-message: "&aReloaded Configuration!"
litebans-do-not-send-silent-punishments: true

#Permissions :
#Reload : bwh.reload

ban:
  enabled: true
  webhook-url: "enter-url-here"
  webhook-title: "New Ban!"
  webhook-thumbnail: "webhook-thumbnail-url-here"

mute:
  enabled: false
  webhook-url: "enter-url-here"
  webhook-title: "New Mute!"
  webhook-thumbnail: "webhook-thumbnail-url-here"

warn:
  enabled: false
  webhook-url: "enter-url-here"
  webhook-title: "New Warn!"
  webhook-thumbnail: "webhook-thumbnail-url-here"

kick:
  enabled: false
  webhook-url: "enter-url-here"
  webhook-title: "New Kick!"
  webhook-thumbnail: "webhook-thumbnail-url-here"
```

If there is any bug. report it to me in discord and i will try to fix it as soon as possible Parsa#7846