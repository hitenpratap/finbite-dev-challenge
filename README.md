# finbite-dev-challenge
Problem statement:

Create a method for calculating invoice total, which returns invoice total and prints out invoice rows.

calculateInvoiceTotal(usage, priceList, package)

Package S
10 minutes
50 sms
5 eur

Package M
50 minutes
100 sms
10 eur

Package L
500 minutes
500 sms
20 eur

Price list
1 minute - 0.2 eur
1 sms - 0.3 eur

Assumptions:
- There are 3 packages given with a base price and allowed MINUTES and SMS with-in that base price.
- If users usage exceeds what is provided for a given package then excess usage would be calculated using price list given.
- I tried to make the code flexible so that any other type can be added like EMAIL along with SMS and MINUTES.
- Added few such cases in the tests
- Code can be run using Docker/Makefile without having to install any dependencies.

Commands:
- Make run
- Make clean
