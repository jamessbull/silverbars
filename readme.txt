Hi,

I decided to go for a functional approach with this because the lack
of mutation makes it easier to reason about and it is more amenable to
being parallelised.

And I wanted to have a go :)

There are several things which occur to me about this

1) What about prices which are not to the nearest penny?
    If this was a requirement then I would group items
    by prices where the number of decimal places has been set to two.

2) What about persistence and so forth?
    When persistence becomes necessary we will use the repository pattern
    to persist the order board.

3) What if we want alternative units or to buy or sell a different commodity?
    Depending on exactly what the requirements were we would change the order
    model to include a commodity and introduce units.

In general I have tried to keep it as simple as possible. Everything should be
as simple as possible but no simpler. :)

The obligatory exceptions to this in this case are introducing a currency and
using a BigDecimal rather than a whole number of pennies.

I just thought that I could plausibly claim that a place that sells only
silver bars is less likely to branch out into Lead bars than it is to sell some
silver to another country. Only taking orders in whole pennies in GBP
seemed to be unlikely.

As with all these things an ambiguity is an opportunity to ask questions and
open a dialog with the business about what they really want so we don't have to
guess.

If you have any feedback for me I would be very grateful.

Many thanks,

Jim
