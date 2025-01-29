
# aws bug

Apparently if you call `s3.listObjectsV2()` with a null bucket name, the
exception causes us to not close the context, and subsequent spans will be 
parented incorrectly.

