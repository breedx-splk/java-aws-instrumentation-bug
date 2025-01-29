
# aws bug

Apparently if you call `s3.listObjectsV2()` with a null bucket name, the
exception causes us to not close the context, and subsequent spans will be 
parented incorrectly.

## running

I just run the app from intellij:

<img width="718" alt="image" src="https://github.com/user-attachments/assets/c42b89b8-b96f-4f04-a7ab-1acd91a70ea4" />
