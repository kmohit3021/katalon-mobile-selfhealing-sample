import mk.katalon.helpers.MobileElementFinder


String input = """ID # 0984692 002
3-PACK REGULAR COTTON T-SHIRTS
€59.00
x17
Color: 
Black"""

String input2 = "Mark"

bestValue = MobileElementFinder.getBestStableSubstringFromText(input)

println "//*[contains(normalize-space(), '"+bestValue+"')]"