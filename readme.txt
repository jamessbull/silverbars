Hi,
A few notes on my thoughts.

Functional approach

I decided to go for a functional approach with this because the lack
of mutation makes it easier to reason about and it is more amenable to
being run in parallel if that becomes necessary.

I am interested in learning more about functional programming and certainly
would not consider mhself to be an expert.

It might be the case that a procedural approach would be faster.


Performance

I decided that as we do not have requirements for the performance specified
and the tests seem to run quickly I would not write a performance test until
such time as the performance of the system drew comment.

I would at this point test how the performance of the board held up as the
following variables changed

1) The number of orders being registered per second
2) The number of times a summary was asked for per second.

If a problem was highlighted I would then give some thought as to what we
could try to speed things up. Some approaches are increasing
the number of machines it runs on, pushing info out rather than being polled
separating the registering of orders from the summarisation or just trying
some simple refactoring to see if a procedural approach would get a big gain.


Ambiguous/missing requirements

1) What about prices which are not to the nearest penny?
    If this was a requirement then I would group items
    by prices where the number of decimal places has been set to two.

2) What about persistence and so forth? What do we do if it becomes required?
    When persistence becomes necessary we will use the repository pattern
    to persist the order board.

3) What if we want alternative units or to buy or sell a different commodity?
    Depending on exactly what the requirements were we would change the order
    model to include a commodity and introduce units.

4) What if there is an error and a system tries to submit the same order twice?
    The summary would be wrong!
    To work around this we could add an id (probably a GUID) to each order we
    create that way we can check if we have added that specific order before
    rather than simply assuming each order will only be added once as we do at
    the moment.

In general I have tried to keep most things as simple as possible.

The exceptions to this are introducing a currency and
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
