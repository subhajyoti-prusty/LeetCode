# 20

# Given a string s containing just the characters '(', ')', '{', '}', '[' and ']', determine if the input string is valid.

# An input string is valid if:

# Open brackets must be closed by the same type of brackets.
# Open brackets must be closed in the correct order.
# Every close bracket has a corresponding open bracket of the same type.
 

# Example 1:

# Input: s = "()"
# Output: true
# Example 2:

# Input: s = "()[]{}"
# Output: true
# Example 3:

# Input: s = "(]"
# Output: false
 

# Constraints:

# 1 <= s.length <= 104
# s consists of parentheses only '()[]{}'.

class Solution(object):
    def isValid(self, s):
        """
        :type s: str
        :rtype: bool
        """
        stack = []
        mapping = {')': '(', '}': '{', ']': '['}
        for i in s:
            if i in mapping:
                top = stack.pop() if stack else '#'
                if mapping[i] != top:
                    return False
            else:
                stack.append(i)
        return not stack
    
# Time complexity: O(n)
# Space complexity: O(n)
# Runtime: 16 ms
# Memory Usage: 13.6 MB

# Test case
s = Solution()
assert s.isValid("()") == True
assert s.isValid("()[]{}") == True
assert s.isValid("(]") == False
assert s.isValid("([)]") == False
assert s.isValid("{[]}") == True
assert s.isValid("]") == False
    
    
# ANOTHER SOLUTION
  
# class Solution(object):
#     def isValid(self, s):
#         """
#         :type s: str
#         :rtype: bool
#         """
#         if len(list(s)) % 2 != 0:
#             return False
#         L = []
#         for x in s:
#             if x == "(":
#                 L.append(")")
#             elif x == "[":
#                 L.append("]")
#             elif x == "{":
#                 L.append("}")
#             elif len(L) != 0 and x == L.pop():
#                 continue
#             else:
#                 return False
#         if len(L) != 0:
#             return False
#         return True
    