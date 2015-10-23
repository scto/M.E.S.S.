## AI

Is charged to make an entity move

Entity >> *register* >> AI

Entity << *send request* << AI

### Request - Could have a undo

+ Move : Add to x, y and angle

## Canonnier

Is charged to make an entity shoot. Handling the angle of the shot.

Entity >> *register* >> Canonnier
Entity >> *set weapon* >> Canonier

Entity << *send order* << Canonnier

### Order

+ Shoot
