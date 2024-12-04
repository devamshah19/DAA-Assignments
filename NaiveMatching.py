def naive_string_match(text, pattern):
    for i in range(len(text) - len(pattern) + 1):
        if text[i:i + len(pattern)] == pattern:
            return i
    return -1

text = "hello world"
pattern = "not"
result = naive_string_match(text, pattern)
if result != -1:
    print(f"Pattern found at index {result}")
else:
    print("Pattern not found")


