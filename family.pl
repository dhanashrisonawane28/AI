% Facts:
male(paikuji).
male(anneji).
male(annaji).
male(kishor).
male(kiran).
male(ravindra).
male(sanjay).
male(chandrashekhar).
male(aniket).
male(anuj).
male(kartik).
male(atharva).
male(kaivalya).
male(mandar).
female(vanita).
female(sheela).
female(niraja).
female(sheetal).
female(manisha).
female(mai).
female(sangita).
female(madhura).

parent(paikuji, anneji).
parent(paikuji, annaji).
parent(anneji, kishor).
parent(anneji, kiran).
parent(anneji, neeta).
parent(annaji, ravindra).
parent(annaji, sanjay).
parent(annaji, mai).
parent(annaji, chandrashekhar).
parent(vanita, ravindra).
parent(vanita, sanjay).
parent(vanita, mai).
parent(vanita, chandrashekhar).
parent(ravindra, anuj).
parent(ravindra, kartik).
parent(sheetal, anuj).
parent(sheetal, kartik).
parent(sanjay, atharva).
parent(sanjay, kaivalya).
parent(manisha, atharva).
parent(manisha, kaivalya).
parent(chandrashekhar, madhura).
parent(chandrashekhar, mandar).
parent(sangita, madhura).
parent(sangita, mandar).


husband(annaji, vanita).
husband(kishor, sheela).
husband(kiran, niraja).
husband(ravindra, sheetal).
husband(sanjay, manisha).
husband(chandrashekhar, sangita).

% Rules:
father(X, Y) :- male(X), parent(X, Y).
mother(X, Y) :- female(X), parent(X, Y).

son(X, Y) :- male(X), parent(Y, X).
daughter(X, Y) :- female(X), parent(Y, X).

wife(X, Y) :- husband(Y, X).

grandfather(X, Z) :- father(X, Y), parent(Y, Z).
grandmother(X, Z) :- mother(X, Y), parent(Y, Z).

sibling(X, Y) :- parent(Z, X), parent(Z, Y), X \= Y.
cousin(X, Y) :- parent(Z, X), parent(W, Y), sibling(Z, W).

uncle(X, Y) :- parent(Z, Y), sibling(X, Z), male(X);grandfather(Y,W),sibling(W,V),son(Y,V).
aunt(X, Y) :- father(Z, Y), sibling(Z, W), wife(X,W);parent(Z,Y),sibling(X,Z),female(X).

nephew(X,Y) :- uncle(Y,X).
neice(X,Y) :- aunt(Y,X).

brother_in_law(X, Y) :- husband(Z, X), sibling(Z, Y).
sister_in_law(X, Y) :- husband(Z, X), sibling(Z, W), wife(Y, W);husband(Y,W),sibling(W,X).
cousin_in_law(X, Y) :- cousin(Z, X), wife(Y, Z).

father_in_law(X, Y) :- husband(Z, Y), father(X, Z).
mother_in_law(X, Y) :- husband(Z, Y), mother(X, Z).
uncle_in_law(X, Y) :- husband(Z, Y), uncle(X, Z).
aunt_in_law(X, Y) :- husband(Z, Y), aunt(X, Z).

grandfather_in_law(X, Y) :- husband(X, Z), grandfather(Z, Y).

great_uncle(X, Y) :- uncle(X, Z), parent(Z, Y).
great_aunt(X, Y) :- aunt(X, Z), parent(Z, Y).

great_daughter_in_law(X,Y) :-grandfather(X,Z),husband(Z,Y).

second_cousin(X, Y) :- grandfather(Z, X), grandfather(W,Y),sibling(W, Z).
paternal_cousin(X, Y) :- grandfather(Z, Y), father(W, X), sibling(Z, W).

great_grandfather(X, Z) :- father(X, Y), grandfather(Y, Z).

find_relationship(X, Y) :-
    father(X, Y), write(X), write(' is the father of '), write(Y), nl.
find_relationship(X, Y) :-
    mother(X, Y), write(X), write(' is the mother of '), write(Y), nl.
find_relationship(X, Y) :-
    son(X, Y), write(X), write(' is the son of '), write(Y), nl.
find_relationship(X, Y) :-
    daughter(X, Y), write(X), write(' is the daughter of '), write(Y), nl.
find_relationship(X, Y) :-
    husband(X, Y), write(X), write(' is the husband of '), write(Y), nl.
find_relationship(X, Y) :-
    wife(X, Y), write(X), write(' is the wife of '), write(Y), nl.
find_relationship(X, Y) :-
    grandfather(X, Y), write(X), write(' is the grandfather of '), write(Y), nl.
find_relationship(X, Y) :-
    sibling(X, Y), write(X), write(' is the sibling of '), write(Y), nl.
find_relationship(X, Y) :-
    father(Z, Y), mother(X, Z), write(X), write(' is the grandmother of '), write(Y), nl.
find_relationship(X, Y) :-
    father_in_law(X, Y), write(X), write(' is the father-in-law of '), write(Y), nl.
find_relationship(X, Y) :-
    mother_in_law(X, Y), write(X), write(' is the mother-in-law of '), write(Y), nl.
find_relationship(X, Y) :-
    brother_in_law(X, Y), write(X), write(' is the brother-in-law of '), write(Y), nl.
find_relationship(X, Y) :-
    sister_in_law(X, Y), write(X), write(' is the sister-in-law of '), write(Y), nl.
find_relationship(X, Y) :-
    cousin_in_law(X, Y), write(X), write(' is the cousin-in-law of '), write(Y), nl.
find_relationship(X, Y) :-
    uncle_in_law(X, Y), write(X), write(' is the uncle-in-law of '), write(Y), nl.
find_relationship(X, Y) :-
    aunt_in_law(X, Y), write(X), write(' is the aunt-in-law of '), write(Y), nl.
find_relationship(X, Y) :-
    grandfather_in_law(X, Y), write(X), write(' is the grandfather-in-law of '), write(Y), nl.
find_relationship(X, Y) :-
    great_uncle(X, Y), write(X), write(' is the great-uncle of '), write(Y), nl.
find_relationship(X, Y) :-
    great_aunt(X, Y), write(X), write(' is the great-aunt of '), write(Y), nl.
find_relationship(X, Y) :-
    uncle(X, Y), write(X), write(' is the uncle of '), write(Y), nl.
find_relationship(X, Y) :-
    aunt(X, Y), write(X), write(' is the aunt of '), write(Y), nl.
find_relationship(X, Y) :-
    cousin(X, Y), write(X), write(' is the cousin of '), write(Y), nl.
find_relationship(X, Y) :-
    second_cousin(X, Y), write(X), write(' is the second cousin of '), write(Y), nl.
find_relationship(X, Y) :-
    paternal_cousin(X, Y), write(X), write(' is the paternal cousin  of '), write(Y), nl.
find_relationship(X, Y) :-
    great_grandfather(X, Y), write(X), write(' is the great-grandfather of '), write(Y), nl.
find_relationship(X, Y) :-
    great_daughter_in_law(X, Y), write(Y), write(' is the great-daughter-in-law of '), write(X), nl.
find_relationship(X, Y) :-
    nephew(X, Y), write(X), write(' is the nephew of '), write(Y), nl.
find_relationship(X, Y) :-
    neice(X, Y), write(X), write(' is the neice of '), write(Y), nl.
find_relationship(X, Y) :- write('Relationship not defined between '), write(X), write(' and '), write(Y), nl.

































