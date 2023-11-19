import tkinter as tk
from tkinter import messagebox
from PIL import Image, ImageTk  # install pillow with pip: pip install pillow
import random
from tkinter import StringVar
from mariage_stable import *;
class FirstPage(tk.Frame):
    def __init__(self, parent, controller):
        tk.Frame.__init__(self, parent)
 
      
        # Charger l'image
        image = Image.open("photo_univ.jpg")  # Remplacez "votre_image.jpg" par le chemin de votre image
        photo = ImageTk.PhotoImage(image)

       # Créer un canvas pour placer l'image
        canvas = tk.Canvas(self, width=photo.width(), height=photo.height())
        canvas.pack(fill="both", expand=True)

        label = tk.Label(self,text="ParcourSoup", font=("Aria Bold",50))
       
        label.place(x=230, y=30)
        texte_label = tk.Label(self, text="L'application ou l'avenir se dessine",font=("Aria ",25))
        texte_label.place(x=200, y=130)

        Button = tk.Button(self, text= "Entrez votre préférence",font=(
            "Arial", 15), command= lambda: controller.show_frame(SecondPage))
        Button2 = tk.Button(self,font=(
            "Arial", 15), text= "Lancer la simulation", command= lambda: controller.show_frame(ThirdPage))
        Button.place(x=450, y=430)
        Button2.place(x=100, y=430)
        
 
class SecondPage(tk.Frame):
    def __init__(self, parent, controller):
        tk.Frame.__init__(self, parent)
 
        label = tk.Label(self,text="Faites vos voeux", font=("Aria Bold",40))
        label.place(x=230, y=30)
        texte_label = tk.Label(self, text="Selectionnez l'établissement de votre choix !",font=("Aria ",20))
        texte_label.place(x=200, y=130)
        
        # Cases à cocher
        check_var1 = tk.IntVar()
        checkbox1 = tk.Checkbutton(self, text="Académie de Montpellier", variable=check_var1)
        checkbox1.place(x=250, y=230)

        check_var2 = tk.IntVar()
        checkbox2 = tk.Checkbutton(self, text="Académie de Toulouse", variable=check_var2)
        checkbox2.place(x=250, y=280)

        check_var1 = tk.IntVar()
        checkbox1 = tk.Checkbutton(self, text="Académie de Lyon", variable=check_var1)
        checkbox1.place(x=250, y=330)
        
        
        Button = tk.Button(self, text="Valider", font=(
            "Arial", 15), command=lambda: controller.show_frame(FirstPage))
        Button.place(x=450, y=400)
 
    
 
        Button = tk.Button(self, text="Retour à la page principale", font=(
            "Arial", 10), command=lambda: controller.show_frame(FirstPage))
        Button.place(x=100, y=450)
 
class ThirdPage(tk.Frame):

    def __init__(self, parent, controller):
        tk.Frame.__init__(self, parent)
        self.controller = controller 

        label = tk.Label(self,text="A vous de jouez !", font=("Aria Bold",40))
        label.place(x=230, y=30)
        texte_label = tk.Label(self, text="Selectionnez les criteres de votre choix !",font=("Aria ",20))
        texte_label.place(x=200, y=130)
        # Champs d'entrée
         
        self.label1 = tk.Label(self, text="Entrez le nombre d'étudiants : ")
        self.label1.place(x=220, y=250)
        
        #Initialisation des strings pour les afficher 
        self.nb_pref = tk.StringVar()
        self.result_etudiants = tk.StringVar()
        self.result_ecoles = tk.StringVar()
        
        self.var_entry = tk.Entry(self,textvariable=self.nb_pref)
        self.var_entry.place(x=390, y=250)
          
        generateur_button = tk.Button(self,text="Générer les préférences",command=self.generateurPrefContainer)
        generateur_button.place(x=230,y=330)

        result_label_etudiants = tk.Label(self,textvariable=self.result_etudiants) 
        result_label_etudiants.place(x=10,y=300)
        result_label_ecoles = tk.Label(self,textvariable=self.result_ecoles) 
        result_label_ecoles.place(x=10,y=350)

        Button2 = tk.Button(self,font=("Arial", 15), text= "Lancer la simulation", command= lambda: self.valider(self.result_etudiants, self.result_ecoles))
        Button2.place(x=430, y=330)
     
        Button = tk.Button(self, text="Retour à la page principale", font=("Arial", 10), command=lambda: controller.show_frame(FirstPage))
        Button.place(x=10, y=450)
    def generateurPrefContainer(self):
            
            if(self.nb_pref.get().isnumeric()):
                
                self.var_entry.select_clear()
                self.label1.config(fg='black')

                # Longueur 
                nb_etu = int(self.nb_pref.get())
                nb_eco = int(self.nb_pref.get())
                # Générer
                etudiants, ecole = generateurPref(nb_etu, nb_eco)

                self.result_etudiants.set(etudiants)
                self.result_ecoles.set(ecole)  
            else : 
                self.var_entry.select_clear()
                self.label1.config(fg='red')

    def valider(self,entry1,entry2):
        # Récupérer les valeurs des champs d'entrée
        valeur_entree1 = entry1.get()
        valeur_entree2 = entry2.get()
   
        # Afficher les valeurs récupérées
        print("Valeur 1:", valeur_entree1, ", type : ", type(valeur_entree1))
        print("Valeur 2:", valeur_entree2)

        # On vérifie que la génération de Voeux est bien été lancée 
        if( valeur_entree1 == None or valeur_entree1 == "" ): 
            self.var_entry.select_clear()
            self.label1.config(fg='red')
        else : 
            self.var_entry.select_clear()
            self.label1.config(fg='black')

            self.controller.etudiants_choix = valeur_entree1
            self.controller.ecoles_choix = valeur_entree2
            self.controller.frames[ResultPage].correct_label()
            self.controller.show_frame(ResultPage)
  
class ResultPage(tk.Frame):

    def __init__(self, parent, controller):
        tk.Frame.__init__(self, parent)
        self.controller = controller 

        label = tk.Label(self,text="Résultat admission !", font=("Aria Bold",40))
        label.place(x=230, y=30)
        texte_label = tk.Label(self, text="Selectionnez les criteres de votre choix !",font=("Aria ",20))
        texte_label.place(x=200, y=130)
        # Champs d'entrée
         
        label_choix_etudiants = tk.Label(self, text="Choix des étudiants : ")
        label_choix_etudiants.place(x=50, y=250)

        self.choix_etudiants = tk.Label(self, text=" choix ")
        self.choix_etudiants.place(x=200, y=250)     

        label_choix_ecoles = tk.Label(self, text="Choix des écoles : ")
        label_choix_ecoles.place(x=50, y=350)

        self.choix_ecoles = tk.Label(self, text=" choix ")
        self.choix_ecoles.place(x=200, y=350)    

        #Initialisation des strings pour les afficher 
        self.nb_pref = tk.IntVar()  
        self.result_etudiants = tk.StringVar()
        self.result_ecoles = tk.StringVar()
          

        result_label_etudiants = tk.Label(self,textvariable=self.result_etudiants) 
        result_label_etudiants.place(x=10,y=300)
        result_label_ecoles = tk.Label(self,textvariable=self.result_ecoles) 
        result_label_ecoles.place(x=10,y=350)
     
        Button = tk.Button(self, text="Retour à la page principale", font=("Arial", 10), command=lambda: controller.show_frame(FirstPage))
        Button.place(x=10, y=450)

    def correct_label(self):
        self.choix_etudiants.config(text=self.controller.etudiants_choix)  
        self.choix_ecoles.config(text=self.controller.ecoles_choix )  

            

class Application(tk.Tk):

    def __init__(self, *args, **kwargs):
        tk.Tk.__init__(self, *args, **kwargs)
 
        # creating a window
        window = tk.Frame(self)
        window.pack()
 
        window.grid_rowconfigure(0, minsize=500)
        window.grid_columnconfigure(0, minsize=800)
 
        self.frames = {}
        for F in (FirstPage, SecondPage,ThirdPage,ResultPage):
            frame = F(window, self)
            self.frames[F] = frame
            frame.grid(row=0, column=0, sticky="nsew")
 
        self.show_frame(FirstPage)
    def show_frame(self, page):
        frame = self.frames[page]
        frame.tkraise()
        self.title("ParcourpSoup")

   
 
app = Application()
app.maxsize(800, 500)
app.mainloop()