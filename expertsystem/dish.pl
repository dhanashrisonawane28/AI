dish(puran_poli) :-
    verify(sweet),
    verify(pancake),
    verify(stuffed),
    verify(traditional).

dish(misal_pav) :-
    verify(spicy),
    verify(curry),
    verify(chickpeas),
    verify(potato).

dish(vada_pav) :-
    verify(snack),
    verify(potato),
    verify(fried),
    verify(bread).

dish(bhel_puri) :-
    verify(snack),
    verify(puffed_rice),
    verify(chutney),
    verify(onion).

dish(sabudana_khichdi) :-
    verify(fasting),
    verify(tapioca),
    verify(peanut).

dish(thalipeeth) :-
    verify(savoury),
    verify(multigrain),
    verify(flatbread),
    verify(onion).

ask(Question) :-
    write('Does the dish include '),
    write(Question),
    write('? '),
    read(Response),
    nl,
    ( (Response == yes ; Response == y)
    ->
        assert(yes(Question));
        assert(no(Question)), fail).

:- dynamic yes/1,no/1.

verify(S) :-
    (yes(S) -> true ; (no(S) -> fail ; ask(S))).

undo :- retract(yes(_)),fail.
undo :- retract(no(_)),fail.
undo.

start :- dish(Dish),
    write('The dish is: '),
    write(Dish),
    nl,
    undo,
    nl.
