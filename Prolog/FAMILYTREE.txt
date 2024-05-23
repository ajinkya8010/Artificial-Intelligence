% Facts for family relationships
father(john, mike).
father(john, lisa).
mother(mary, mike).
mother(mary, lisa).
brother(mike, lisa).
sister(lisa, mike).

% Facts for height (in cm)
height(john, 180).
height(mary, 165).
height(mike, 170).
height(lisa, 160).

% Rule to check if someone is a father
is_father(Father, Child) :-
    father(Father, Child).

% Rule to check if someone is a mother
is_mother(Mother, Child) :-
    mother(Mother, Child).

% Rule to check if someone is a brother
is_brother(Brother, Sibling) :-
    brother(Brother, Sibling).

% Rule to check if someone is a sister
is_sister(Sister, Sibling) :-
    sister(Sister, Sibling).

% Rule to check who is taller
is_taller(Person1, Person2) :-
    height(Person1, Height1),
    height(Person2, Height2),
    Height1 > Height2.

check_taller :-
    write('Enter the name of the first person: '),
    read(Person1),
    write('Enter the name of the second person: '),
    read(Person2),
    (is_taller(Person1, Person2) ->
        format('~w is taller than ~w.~n', [Person1, Person2]);
        format('~w is not taller than ~w.~n', [Person1, Person2])).

% Load the Prolog file
%?- ['family_tree.pl'].

% Check if John is the father of Mike
%?- is_father(john, mike).

% Check if Mary is the mother of Lisa
%?- is_mother(mary, lisa).

% Check if Mike is the brother of Lisa
%?- is_brother(mike, lisa).

% Check if Lisa is the sister of Mike
%?- is_sister(lisa, mike).

% Check if John is taller than Mary
%?- is_taller(john, mary).

% Check if Mike is taller than Lisa
%?- is_taller(mike, lisa).
%?- check_taller.
%Enter the name of the first person: mary.
%Enter the name of the second person: |: john.
%mary is not taller than john.
%true.
