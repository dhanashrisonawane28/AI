% Define vacation options and their attributes.
vacation_option(beach, hot, all_inclusive, relaxing, tropical, yes, moderate).
vacation_option(mountain, moderate, self_catered, adventurous, scenic, no, moderate).
vacation_option(city, moderate, self_catered, cultural, urban, yes, high).
vacation_option(safari, hot, all_inclusive, adventurous, wildlife, yes, high).

% Define rules to recommend vacation options based on user preferences.
recommend_vacation(User, VacationOption) :-
    ask_vacation_preferences(User, Temperature, Accommodation, Activity, Landscape, Budget, BudgetRange),
    vacation_option(VacationOption, Temperature, Accommodation, Activity, Landscape, BudgetFriendly, _),
    BudgetFriendly = Budget,
    member(Budget, BudgetRange),
    display_vacation_attributes(VacationOption).

% Display the recommended vacation and its attributes.
display_vacation_attributes(VacationOption) :-
    vacation_option(VacationOption, Temperature, _, Accommodation, Activity, Landscape, _, _),
    write('Based on your preferences, we recommend the following vacation option:'), nl,
    write('Vacation Type: '), write(VacationOption), nl,
    write('Preferred Temperature: '), write(Temperature), nl,
    write('Accommodation Type: '), write(Accommodation), nl,
    write('Recommended Activities: '), write(Activity), nl,
    write('Preferred Landscape: '), write(Landscape), nl.

% Ask the user about their vacation preferences.
ask_vacation_preferences(User, Temperature, Accommodation, Activity, Landscape, Budget, BudgetRange) :-
    write('Hello, '), write(User), write('! Please answer the following questions to help us recommend a suitable vacation destination:'), nl,
    write('1. What temperature do you prefer? (hot/moderate)'),
    read(Temperature),
    write('2. What type of accommodation do you prefer? (all_inclusive/self_catered)'),
    read(Accommodation),
    write('3. What kind of activities do you enjoy? (relaxing/adventurous/cultural)'),
    read(Activity),
    write('4. What type of landscape do you prefer? (tropical/scenic/urban/wildlife)'),
    read(Landscape),
    write('5. What is your budget range? (low/moderate/high)'),
    read(Budget),
    get_budget_range(Budget, BudgetRange).

% Map budget levels to their respective ranges.
get_budget_range(low, [low, moderate]).
get_budget_range(moderate, [moderate, high]).
get_budget_range(high, [high]).
