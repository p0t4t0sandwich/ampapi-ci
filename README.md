# ampapi-ci

A simple CI setup to integrate with AMP and run tests

- [ ] triggered when a build finishes
  - [ ] queue system for trusted users (handle via discord bot)
- [ ] options to specify version ranges and mod loaders to test
- [ ] options to include download URLs for specific mod dependancies to test
- [ ] option to wipe server files, just the world folder, just the configs, etc
- [ ] save metadata on each run with version/modloader/dependancy info
  - [ ] auto-wipe if there’s a mismatch on the next run (unless specified otherwise)
  - [ ] better: include in TaterLib filestore as auto-generated metadata
- [ ] start the server
  - [ ] send back started webhook using AMP's scheduler
- [ ] finish test after specific keyword is sent - "Test complete"
  - [ ] send back finished webhook using AMP's scheduler
- [ ] send back logs using MCLogs
- [ ] if there’s a crash, send webhook
