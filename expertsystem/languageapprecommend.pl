% Define facts about language options and their attributes.
language_option("Duolingo", beginner, gamified, free, vocabulary_building, yes, user_community, android_ios, monthly_subscription, offline_mode,yes, multiple_languages).
language_option("Rosetta Stone", beginner, structured, paid, pronunciation_practice, no, tutor_support, pc_mac, yearly_subscription, no_offline,no, single_language).
language_option("Babbel", beginner, interactive, paid, cultural_insights, no, speech_recognition, android_ios, monthly_subscription, offline_mode,no, multiple_languages).
language_option("Memrise", intermediate, gamified, free, memorization_techniques, yes, leaderboards, android_ios, monthly_subscription, offline_mode,no, multiple_languages).
language_option("italki", advanced, interactive, paid, one-on-one_tutoring, yes, language_exchange, web_app, hourly_payment, no_offline,no, multiple_languages).
language_option("BBC Languages", beginner, audio, free, listening_comprehension, yes, pronunciation_guide, web, no_subscription, no_offline,no, multiple_languages).
language_option("Pimsleur", intermediate, audio, paid, pronunciation_drills, yes, no_support, cd, one-time_purchase, no_offline,no, single_language).
language_option("Transparent Language", advanced, structured, paid, cultural_insights, no, tutor_support, pc_mac, yearly_subscription, offline_mode,no, multiple_languages).
language_option("FluentU", intermediate, video, paid, cultural_insights, no, user_community, android_ios, monthly_subscription, offline_mode,no, multiple_languages).
language_option("Busuu", beginner, structured, free, vocabulary_building, yes, language_exchange, android_ios, monthly_subscription, offline_mode,no, multiple_languages).


% Define rules to recommend language options based on user preferences.
recommend_product(User, LanguageResource) :-
	ask_product_preferences(User, Proficiency, LearningStyle, Cost, Features, Kidfriendly, AdditionalSupport, Platform, SubscriptionType, OfflineAvailability,Certificate, MultiLanguageSupport),
    (language_option(LanguageResource, Proficiency, LearningStyle, Cost, Features, Kidfriendly, AdditionalSupport, Platform, SubscriptionType, OfflineAvailability,Certificate, MultiLanguageSupport) ->
	display_product_attributes(LanguageResource);
        write('Sorry, no suitable language option available based on your preferences.'), nl).

% Display the ignored language attributes when a match is found.
display_product_attributes(LanguageResource) :-
    language_option(LanguageResource, Proficiency, LearningStyle, Cost, Features, Kidfriendly, AdditionalSupport, Platform, SubscriptionType, OfflineAvailability,Certificate, MultiLanguageSupport),
    write('We recommend the following language learning resource: '), nl,
    write('Resource: '), write(LanguageResource), nl,
    write('Proficiency Level: '), write(Proficiency), nl,
    write('Learning Style: '), write(LearningStyle), nl,
    write('Cost: '), write(Cost), nl,
    write('Features: '), write(Features), nl,
    write('Kid Friendly: '), write(Kidfriendly), nl,
    write('Additional Support: '), write(AdditionalSupport), nl,
    write('Platform: '), write(Platform), nl,
    write('Subscription Type: '), write(SubscriptionType), nl,
    write('Offline Availability: '), write(OfflineAvailability), nl,
    write('Certfications: '), write(Certificate), nl,
    write('Multi-Language Support: '), write(MultiLanguageSupport), nl.

% Ask the user about their laptop preferences.
ask_product_preferences(User, Proficiency, LearningStyle, Cost, Features, Kidfriendly, AdditionalSupport, Platform, SubscriptionType, OfflineAvailability,Certificate,  MultiLanguageSupport) :-
     write('Hello, '), write(User), write('!'), nl,
    write('1. What is your current language learning proficiency level? (beginner/intermediate/advanced)'),
    read(Proficiency), nl,
    write('2. What type of language learning style do you prefer?'), nl,
    write('   a. Gamified (games and challenges)'), nl,
    write('   b. Structured (formal lessons and courses)'), nl,
    write('   c. Interactive (conversational and interactive tools)'), nl,
    write('   d. Audio (listening and speaking)'), nl,
    read(LearningStyle), nl,
    write('3. Do you have a cost preference for language learning resources?'), nl,
    write('   a. Free'), nl,
    write('   b. Paid'), nl,
    read(Cost), nl,
    write('4. What specific features are you looking for? (e.g., vocabulary building, pronunciation practice,cultural_insights,memorization_techniques,one-on-one_tutoring,listening_comprehension,pronunciation_drills)'),
    read(Features), nl,
    write('5. is your app kid friendly?(yes/no)'),
    read(Kidfriendly), nl,
    write('6. What additional resources do you want? (user_community,tutor_support ,speech_recognition ,leaderboards,language_exchange,pronunciation_guide)'),
    read(AdditionalSupport), nl,
    write('7. What platform do you prefer to use for language learning? (e.g., android_ios, pc_mac, web, cd, etc.)'),
    read(Platform), nl,
    write('8. What type of subscription do you prefer? (e.g., monthly, yearly, one-time purchase)'),
    read(SubscriptionType), nl,
    write('9. Do you want offline availability? (offline_mode,no_offline )'),
    read(OfflineAvailability), nl,
    write('10. Do you any certification app? (yes/no)'),
    read(Certificate), nl,
    write('11. Do you need support for learning multiple languages? (eg :- single_language,multiple_languages)'),
    read(MultiLanguageSupport), nl.
