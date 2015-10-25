# Enemies birth

## Game Orchestrator

+ Uses patterns to make an enemy appear
+ Pick a pattern with the same type has the previous one
+ Pick which enemy will appear on this nest
+ Has a growing tendancy to choose a pattern a different type
+ Stop spawning when true epic boss appear

It has to pay which pattern it will use, then which enemy it will spawn. The amount it can afford is linked to the current score

## Pattern

+ Each pattern has one or more **type**
+ Can have one or more wave (empty wave for pause)

## Wave

+ One or more nest
 
## Nest

+ Is a place where the enemy appear.
+ Has a type (side or top)

# To be ironed out

## AI

Is charged to make an entity move

Entity >> *register* >> AI

Entity << *send request* << AI

### Request - Could have a undo

+ Move : Add to x, y and angle

## Canonnier

Is charged to make an entity shoot. Handling the angle of the shot.

Entity >> *register* >> Canonnier

Entity << *send order* << Canonnier

### Order

+ Shoot
