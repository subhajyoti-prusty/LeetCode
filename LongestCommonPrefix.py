# (14)

# Write a function to find the longest common prefix string amongst an array of strings.

# If there is no common prefix, return an empty string "".

 

# Example 1:

# Input: strs = ["flower","flow","flight"]
# Output: "fl"
# Example 2:

# Input: strs = ["dog","racecar","car"]
# Output: ""
# Explanation: There is no common prefix among the input strings.
 

# Constraints:

# 1 <= strs.length <= 200
# 0 <= strs[i].length <= 200
# strs[i] consists of only lowercase English letters.

# Longest Common Prefix

class Solution(object):
    def longestCommonPrefix(self, strs):
        if not strs:
            return ""
        min_len = min([len(s) for s in strs])
        for i in range(min_len):
            for j in range(1, len(strs)):
                if strs[j][i] != strs[j-1][i]:
                    return strs[0][:i]
        return strs[0][:min_len]

s = Solution()
print(s.longestCommonPrefix(["flower","flow","flight"]))


# Optimal Solution

# class Solution(object):
#     def longestCommonPrefix(self, strs):
#         """
#         :type strs: List[str]
#         :rtype: str
#         """
#         p=strs[0]
#         for s in strs[1:]:
#             while not s.startswith(p):
#                 p=p[:-1]
#                 if not p:
#                     return ""
#         return p
    