	 		============TEMA  1  POO - Sheriff of Nottingham===============
Apostol Vasile-Alexandru
Grupa: 324 CD
-------------------------------------------------------------------------------------------------------------------------------------------------------------------
Descriere: 
-Bordgames in care mai multe personaje folosesc anumite strategii pentru a strange cat mai multi bani in decursul a mai multor runde. Fiecare jucator este sherrif odata pe runda , si poate sau nu sa verifice comerciantii in functie de strategia adoptata sau de suma de bani ramasa.

Implementare:
-Pentru implementarea temei am folosit mai multe clase:
	*Player - clasa de baza unde retin la fiecare moment cartile pe care jucatorul le are in mana , daca este sau nu sherrif , id , strategia , cat si cartile pe care acesta a reusit sa le aduca pe taraba. Pe langa aceste atribute mai sus enumerate mai este unul de tip bag , saculetul jucatorului care se umple in fiecare subrunda (cu exceptia subrundei cand jucatorul este sherrif) si pe care sherriful il verifica.
	*Bag - Saculetul propriu zis contine un int care retine posibila mita pe care jucatorul o poate pune pentru a incerca sa mituiasca sherriful si 3 liste care retin bunurile legale bunurile ilegale si ce declara acesta ca are in sac. Sacul se umple la fiecare subrunda . In aceasta clasa se gasesc 3 metode specifice fiecarui tip de jucator in parte , care sunt apelate in functie de strategia acestuia.
	*CheckSheriff - Aceasta clasa contine 2 jucatori jucatorul verificat si sherriful ,  dar si lista cu carti pentru a adauga cartile la sfarsitul pachetului in cazul in care acesta este prins cu carti ilegale sau nedeclarate.La fel ca la bag clasa are 3 metode care se apeleaza in functie de strategia adoptata de jucatorul sherrif.
	*Getranking - Clasa contine o lista cu toti jucatorii , aici se adauga profitul jucatorilor pe bunurile pe care le au pe taraba , bonusul in cazul in care au un bun ilegal pe taraba cat si bonusul special King si Queen acest lucru cu ajutorul celor 2 metode pe care le contine (getmoneyofstand , getkingqueen ). Dupa acordare tuturor banilor si bonusurilor metoda printrank are rolul de a afisa clasamentul final al jocului,

 
