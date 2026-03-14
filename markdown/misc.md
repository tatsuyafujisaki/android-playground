# Examples of configuration changes

- Rotate the device
- Change the language
- Switch between dark and light mode

# Density / dpi / dp / px

* density [(# of 160px) / inch] = dpi / 160
* dp [(1 / 160) inch] = px * (160 / dpi) = px / density

# How to view a DataStore file

```shell
protoc --decode_raw < input.preferences_pb
```
