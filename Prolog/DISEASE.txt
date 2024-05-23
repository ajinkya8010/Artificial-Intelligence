% Facts for diseases (name, type, symptom)
disease(flu, viral, fever).
disease(flu, viral, cough).
disease(common_cold, viral, sneezing).
disease(common_cold, viral, cough).
disease(malaria, parasitic, fever).
disease(malaria, parasitic, chills).
disease(diabetes, chronic, increased_thirst).
disease(diabetes, chronic, frequent_urination).
disease(cancer, chronic, unexplained_weight_loss).

% Facts for contagious diseases
contagious(flu).
contagious(common_cold).

% Rule to check if a disease is curable (diseases other than cancer are considered curable)
is_curable(Disease) :-
    Disease \= cancer.

% Rule to identify a disease based on symptom and type
identify_disease(Symptom, Type, Disease) :-
    disease(Disease, Type, Symptom).

% Rule to check if a disease is contagious
is_contagious(Disease) :-
    contagious(Disease).

% Rule to find diseases by type
disease_by_type(Type, Disease) :-
    disease(Disease, Type, _).

% Predicate to take input and identify a disease
diagnose_disease :-
    write('Enter the symptom: '),
    read(Symptom),
    write('Enter the type: '),
    read(Type),
    (identify_disease(Symptom, Type, Disease) ->
        format('The disease with symptom ~w and type ~w is ~w.~n', [Symptom, Type, Disease]);
        write('No disease found with the given symptom and type.~n')).

% Predicate to check if a disease is curable based on user input
check_curable :-
    write('Enter the name of the disease: '),
    read(Disease),
    (is_curable(Disease) ->
        format('The disease ~w is curable.~n', [Disease]);
        format('The disease ~w is not curable.~n', [Disease])).


% Load the Prolog file
%?- ['disease_diagnosis.pl'].

% Check if a disease is curable
%?- check_curable.

% Identify a disease based on symptom and type
%?- diagnose_disease.

% Check if a disease is contagious
%?- is_contagious(flu).

% Find diseases by type (e.g., viral)
%?- disease_by_type(viral, Disease).
