INFERENCES 


Première étape : Transformer la phrase en graphe (quand il y a un - c'est considéré comme un mot) et mettre en place la visualisation avec BRAT ?
	*chaque mot/noeud doit avoir un id, contenu du noeud :mot, pred, succ, final bool (savoir si c'est le dernier)
	* mettre un [_START] et [_END] 
	* mettre des r_succ entre chat mot 
	*chaque arcs est typés, orientés, pondérés : relation, poids, noeud_depart, _noeud_arrivée
	*nature d'un mot -> verbe, adjectif, nom...? poids positif?, sans succ, final_bool en true automatiquement 

Deuxième étape : Télécharger les mots composés sur le site -> Info fichier termes composés en FR 


Troisième étape : Mettre en place une fonction qui construit un arbre pour les mots composés issus d'un mot en fichier pour chaque (F3 exemmple avec lait)


Quatrième étape : Faire la fonction qui cherche les mots composés dans la phrase (F3) pour celle-ci soit chercher dans le fichier qui regroupe tous les most composés soit regarder la relation r_locution 


Cinquième étape : Faire une recherche de verbe à l'infinitif ( après mots composés ?) Si on le graphe change en mettant en  infinitif alors on relance la recherche de mots composés car le verbe peut faire parti d'un mot composé 



Un mot composé c'est minimum de mots individuels qui peuvent formés un mot ensemble. Ex : "Rez-de-chaussée" pas considéré comme mot composé car il forme déjà un mot avec les "-". 


A FAIRE : 
Continuer les fonctions de mots composés : choisir dans quels fichiers on fait la recherche puis l'implémenter 
Chercher aussi comment stoker les données des fichiers mots_composés de chaque mot 
Faire une fonction pour enchaîner la création du graphe puis la recherche de mot composé et le résultat sur neo4j




Attention : 


-Quand on crée un nouveau neoud on merge pour pas faire de doublons NEO4J

-Vérifier que l'id pred ou succ ne soit pas le même que l'id actuel 

- Les GN sont composés de certaines prépositions ne pas mettre toutes les prépositions, il vaut mieux faire une liste à part des prépositions qui fonctionnent (exemple : "à" fonctionne) F4

- Imposer un tour max dans les relations (quand on fait les inférences transitives, inductive etc...)

- Inférence ne peut pas donner la bonne réponse pour une exception 

- Faire une règle pour simplifier la phrase en cas d'adverbe ou d'adjectif 
Ex : Je mangeais souvent des madelaines -> Je mangeais des madeleines ATTENTION je mangeais jamais des madeleines -> si on l'enlève ca change le contexte 

- Pisse double sens verbe ou nom, tarte maison voir si une tarte peut-être maison ou voir si une maison peut être tarte (moche) 

- Si on trouve plusieurs mots composés incluant le même mot : petit chat roux = petit chat et chat roux, on garde les deux dans le graphe 

- Pour faire une vraie estimation du temps il faut lancer le test 100 fois 

-Ne supprimer aucune relation, les négativer !


Bonus : 
Ajouter le fait de pouvoir poser des questions (F2)




Regarder GPREP