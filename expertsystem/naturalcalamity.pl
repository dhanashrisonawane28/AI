identify_calamity :-
    write('Welcome to the Natural Calamity Identification System!'), nl,
    write('Answer the following questions to identify the type of calamity.'), nl,
    identify_calamity(unknown).

% Identifying the calamity based on answers to yes/no questions.
identify_calamity(Calamity) :-
    write('Is there seismic activity? (yes/no): '),
    read(SeismicActivity),
    process_answer(SeismicActivity, SeismicValue),
    (
        SeismicValue == yes ->
        ask_magnitude(Calamity);
        SeismicValue == no ->
        ask_strong_winds(Calamity);
        identify_calamity(Calamity)
    ).

ask_magnitude(Calamity) :-
    write('Is the magnitude greater than or equal to 7.0? (yes/no): '),
    read(Magnitude),
    process_answer(Magnitude, MagnitudeValue),
    (
        MagnitudeValue == yes ->
        write('It is an earthquake!'), nl,
        precautions_for_earthquake;
        MagnitudeValue == no ->
        ask_ground_shaking(Calamity);
        identify_calamity(Calamity)
    ).

ask_ground_shaking(Calamity) :-
    write('Is there ground shaking? (yes/no): '),
    read(GroundShaking),
    process_answer(GroundShaking, GroundShakingValue),
    (
        GroundShakingValue == yes ->
        write('It is an earthquake!'), nl,
        precautions_for_earthquake;
        GroundShakingValue == no ->
        ask_strong_winds(Calamity);
        identify_calamity(Calamity)
    ).

ask_strong_winds(Calamity) :-
    write('Is there strong winds? (yes/no): '),
    read(StrongWinds),
    process_answer(StrongWinds, StrongWindsValue),
    (
        StrongWindsValue == yes ->
        ask_wind_speed(Calamity);
        StrongWindsValue == no ->
        ask_heavy_rainfall(Calamity);
        identify_calamity(Calamity)
    ).

ask_wind_speed(Calamity) :-
    write('Is the wind speed greater than or equal to 100 mph? (yes/no): '),
    read(WindSpeed),
    process_answer(WindSpeed, WindSpeedValue),
    (
        WindSpeedValue == yes ->
        ask_low_pressure(Calamity);
        WindSpeedValue == no ->
        ask_heavy_rainfall(Calamity);
        identify_calamity(Calamity)
    ).

ask_low_pressure(Calamity) :-
    write('Is there low atmospheric pressure? (yes/no): '),
    read(LowPressure),
    process_answer(LowPressure, LowPressureValue),
    (
        LowPressureValue == yes ->
        write('It is a hurricane!'), nl,
        precautions_for_hurricane;
        LowPressureValue == no ->
        ask_heavy_rainfall(Calamity);
        identify_calamity(Calamity)
    ).

ask_heavy_rainfall(Calamity) :-
    write('Is there heavy rainfall? (yes/no): '),
    read(HeavyRainfall),
    process_answer(HeavyRainfall, HeavyRainfallValue),
    (
        HeavyRainfallValue == yes ->
        ask_river_overflow(Calamity);
        HeavyRainfallValue == no ->
        ask_high_temperature(Calamity);
        identify_calamity(Calamity)
    ).

ask_river_overflow(Calamity) :-
    write('Is there a river overflow? (yes/no): '),
    read(RiverOverflow),
    process_answer(RiverOverflow, RiverOverflowValue),
    (
        RiverOverflowValue == yes ->
        write('It is a flood!'), nl,
        precautions_for_flood;
        RiverOverflowValue == no ->
        ask_high_temperature(Calamity);
        identify_calamity(Calamity)
    ).

ask_high_temperature(Calamity) :-
    write('Is there high temperature? (yes/no): '),
    read(HighTemperature),
    process_answer(HighTemperature, HighTemperatureValue),
    (
        HighTemperatureValue == yes ->
        ask_temperature(Calamity);
        identify_calamity(Calamity)
    ).

ask_temperature(Calamity) :-
    write('Is the temperature greater than or equal to 70 C? (yes/no): '),
    read(Temperature),
    process_answer(Temperature, TemperatureValue),
    (
        TemperatureValue == yes ->
        write('It is a wildfire!'), nl,
        precautions_for_wildfire;
        identify_calamity(Calamity)
    ).

% Process user answers.
process_answer(y, yes).
process_answer(Y, yes) :- Y == 'yes'.
process_answer(n, no).
process_answer(N, no) :- N == 'no'.
process_answer(_, unknown).

% Precautions for each calamity.
precautions_for_earthquake :-
    write('Precautions for an earthquake:'), nl,
    write('- Take cover under a sturdy piece of furniture or find a safe place away from windows.'), nl,
    write('- Stay indoors until the shaking stops and it is safe to go outside.'), nl,
    write('- Be prepared for aftershocks.'), nl.

precautions_for_hurricane :-
    write('Precautions for a hurricane:'), nl,
    write('- Evacuate if authorities recommend or if you are in a vulnerable area.'), nl,
    write('- Board up windows and secure outdoor objects.'), nl,
    write('- Have an emergency kit with essentials like food, water, and first-aid supplies.'), nl.

precautions_for_flood :-
    write('Precautions for a flood:'), nl,
    write('- Move to higher ground if you are in a flood-prone area.'), nl,
    write('- Avoid driving through flooded areas.'), nl,
    write('- Have an emergency kit and be prepared to evacuate if necessary.'), nl.

precautions_for_wildfire :-
    write('Precautions for a wildfire:'), nl,
    write('- Follow evacuation orders if given by authorities.'), nl,
    write('- Clear flammable vegetation and create a defensible space around your home.'), nl,
    write('- Have an emergency kit and stay informed about the fire\'s progress.'), nl.

% Example usage:
% ?- identify_calamity.
