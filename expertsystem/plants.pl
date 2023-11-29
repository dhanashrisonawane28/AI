plant_data(rose, small_to_medium, red, perennial, gardens, flowers, decoration, moderate_growth).
plant_data(tulip, small, various_colors, perennial, gardens, flowers, decoration, moderate_growth).
plant_data(oak_tree, large, green, perennial, forests, leaves, furniture, slow_growth).
plant_data(cactus, small_to-large, green, perennial, deserts, spines, healing, slow_growth).
plant_data(sunflower, medium, yellow, annual, gardens, flowers, oil_production, fast_growth).
plant_data(pine_tree, large, green, perennial, forests, needles, used_in_lumber, slow_growth).
plant_data(orchid, small, various_colors, perennial, gardens, flowers, decoration, slow_growth).
plant_data(maple_tree, large, various_colors, perennial, forests, leaves, furniture, moderate_growth).
plant_data(lavender, small, purple, perennial, gardens, flowers, used_in_aromatherapy, moderate_growth).
plant_data(palm_tree, large, green, perennial, tropical_regions, leaves, decoration, slow_growth).
plant_data(lily, small-to-medium, various_colors, perennial, gardens, flowers, decoration, moderate_growth).
plant_data(fern, small-to-large, green, perennial, forests, fronds, used_in_landscaping, moderate_growth).
plant_data(venus_flytrap, small, green, perennial, wetlands, traps, no_special_use, slow_growth).
plant_data(daffodil, small, yellow, perennial, gardens, flowers, decoration, fast_growth).
plant_data(carnation, small, various_colors, perennial, gardens, flowers, no_special_use, moderate_growth).
plant_data(pineapple, small-to-medium, green, perennial, tropical_regions, fruit, used_as_a_fruit, fast_growth).
plant_data(rosemary, small, blue, perennial, gardens, leaves, used_in_herbal_remedies, moderate_growth).
plant_data(daisy, small, white, perennial, gardens, flowers, decoration, fast_growth).
plant_data(bamboo, large, green, perennial, forests, stems, used_in_construction, fast_growth).
plant_data(clover, small, white, perennial, fields, leaves, no_special_use, fast_growth).
plant_data(cotton, small, white, annual, farms, fibers, textile_production, fast_growth).
plant_data(grapevine, medium, green, perennial, vineyards, grapes, used_in_winemaking, fast_growth).
plant_data(iris, small-to-medium, various_colors, perennial, gardens, flowers, used_in_landscaping, fast_growth).
plant_data(dandelion, small, yellow, perennial, fields, flowers, used_in_herbal_remedies, fast_growth).
plant_data(azalea, small-to-medium, various_colors, perennial, gardens, flowers, decoration, moderate_growth).
plant_data(magnolia, large, white, perennial, gardens, flowers, decoration, moderate_growth).
plant_data(willow_tree, large, green, perennial, wetlands, leaves, furniture, slow_growth).
plant_data(yucca, medium, green, perennial, deserts, leaves, decoration, moderate_growth).
plant_data(hibiscus, medium, various_colors, perennial, gardens, flowers, decoration, moderate_growth).
plant_data(juniper, small-to-medium, green, perennial, gardens, leaves, used_in_landscaping, slow_growth).
plant_data(wisteria, large, various_colors, perennial, gardens, flowers, decoration, moderate_growth).
plant_data(eucalyptus, large, green, perennial, forests, leaves, medicine, fast_growth).
plant_data(marigold, small, various_colors, annual, gardens, flowers, decoration, fast_growth).
plant_data(cedar_tree, large, green, perennial, forests, wood, used_in_construction, slow_growth).

identify_plant(Color, Size, LifeCycle, Habitat, Part, Use, GrowthRate, Plant) :-
    plant_data(Plant, Size, Color, LifeCycle, Habitat, Part, Use, GrowthRate).

identify_plant(_, _, _, _, _, _, _, no_match_found).

start :-
    write('Welcome to the Plant Identifier!'), nl,
    write('Please describe the plant you want to identify.'), nl,
    write('What is the color of the plant? '), read(Color), nl,
    write('What is the size of the plant (small, medium, large, small_to_medium, small_to_large)? '), read(Size), nl,
    write('Is the plant perennial or annual? '), read(LifeCycle), nl,
    write('Where is the plant commonly found (e.g., "gardens," "forests", "deserts", "fields", "vineyard", "wetlands", "tropical_regions")? '), read(Habitat), nl,
    write('What part of the plant do you want to identify (e.g., "flowers," "leaves")? '), read(Part), nl,
    write('How is the plant typically used (e.g., "decoration," "medicine," "used_in_construction," "used_in_herbal_remedies," "used_in_winemaking," "no_special_use")? '), read(Use), nl,
    write('What is the growth rate of the plant (e.g., "slow_growth," "moderate_growth," "fast_growth")? '), read(GrowthRate), nl,
    identify_plant(Color, Size, LifeCycle, Habitat, Part, Use, GrowthRate, Plant),
    (
        Plant \= no_special_use -> write('Based on your description, the plant may be a '), write(Plant), nl
        ; write('No plant identified based on your given information'), nl
    ).


