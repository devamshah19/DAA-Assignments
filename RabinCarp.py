def rabin_karp(text, pattern):
    m = len(pattern)  # Length of the pattern
    n = len(text)     # Length of the text
    base = 256        # Number of characters in the input alphabet
    prime = 101       # A prime number to calculate hash values
    pattern_hash = 0  # Hash value for the pattern
    text_hash = 0     # Hash value for the text
    h = 1             # The value of base^(m-1) % prime

    # Calculate h = (base^(m-1)) % prime
    for i in range(m - 1):
        h = (h * base) % prime

    # Calculate the hash value of the pattern and the first window of the text
    for i in range(m):
        pattern_hash = (base * pattern_hash + ord(pattern[i])) % prime
        text_hash = (base * text_hash + ord(text[i])) % prime

    # Slide the pattern over the text one by one
    for i in range(n - m + 1):
        # Check if the hash values of the pattern and current window of text match
        if pattern_hash == text_hash:
            # If hash values match, compare the actual strings
            if text[i:i + m] == pattern:
                return i  # Pattern found at index i

        # Calculate the hash value for the next window of text
        if i < n - m:
            text_hash = (base * (text_hash - ord(text[i]) * h) + ord(text[i + m])) % prime
            # Make sure the hash value is positive
            if text_hash < 0:
                text_hash += prime

    return -1  # Pattern not found


text = "hello world"
pattern = "world"
result = rabin_karp(text, pattern)
if result != -1:
    print(f"Pattern found at index {result}")
else:
    print("Pattern not found")