# RTP (Random Teleport)

**RTP** is a simple and lightweight Minecraft plugin for Paper/Spigot servers.  
It allows players to teleport to a random safe location within a defined range.

## Features
- Teleports players to a random location.
- Countdown timer with an action bar message before teleporting.
- Automatically finds a safe Y-coordinate for the player.
- Asynchronous chunk loading to reduce lag.
- Prevents teleporting onto dangerous blocks (e.g., lava, fire).

## Installation
1. Download the RTP plugin `.jar` file.
2. Place the `.jar` into your server's `plugins` folder.
3. Restart or reload your server.
   
## Usage
Players can use the command:
  /rtp
This will start a 5-second countdown and then teleport them to a safe random location.

## Configuration
(Current version: No external configuration needed.)  
Default settings are hardcoded, but future versions can add configurable options such as:
- Minimum/maximum teleport distance
- Countdown duration
- Bad block list (lava, fire, etc.)

## Commands
| Command | Description        | Permission Needed |
|:--------|:--------------------|:------------------|
| `/rtp`  | Randomly teleport   | None (any player)  |

## Permissions
- No permissions required (any player can use `/rtp`).

*(Permission support can be added later if needed.)*

## Requirements
- Minecraft Paper or Spigot server 1.16+ (compatible with newer versions as well)

## Known Limitations
- If the world is not pre-generated, teleporting may cause brief chunk loading lag. (Negligible)

## License
This project is licensed under the MIT License.
