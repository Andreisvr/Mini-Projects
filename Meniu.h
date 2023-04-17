///  Project.pp
//
//  Created by Andrei Sviridov  on 06.03.2023.
//Sistem rețea socializare jobs
//Aplicația permite utilizatorului construirea unui CV personalizat care să includă (dar nu să se limiteze)
//la informații precum numele, prenumele, numărul de telefon, poziții anterioare etc.. Totodată,
//utilizatorul aplicației are opțiunea de a vedea CV-urile celorlalți utilizatori. De asemenea, pentru a facilita
//căutarea candidaților pentru un post de muncă, aplicația oferă opțiunea de a căuta CV-uri care conțin
//un cuvânt introdus de utilizator.
#include <stdlib.h>
#include <stdio.h>
#include <string.h>
#include <time.h>
void inapoi(void);
void Stergere(void);
void modificare_CV(void);
void modificare_Oferta(void);

void Prima_pagina()
{fflush(stdout);
    printf("\n                                         JobFinder\n\n     ");
    fflush(stdout);
    printf("                          ---<=Alegeti Optiunea dorita=>---\n\n");
    fflush(stdout);
    printf("                        -<Introduceti cifra ce corespunde optiunii>-\n\n");
    fflush(stdout);
    printf(" 1<- Construirea unui CV propriu \n");
    fflush(stdout);
    printf(" 2<- Solicitarea unui CV/CV-uri dupa id sau ocupatie  \n");
    fflush(stdout);
    printf(" 3<- Solicitarea joburilor disponibile\n");
    fflush(stdout);
    printf(" 4<- Depunerea unei oferte de job\n");
    fflush(stdout);
  //  printf(" 5<- Modificare/Stergere oferta\n");
   // fflush(stdout);
    printf(" 5<- Exit\n");

}

 struct CV {
        char Nume_Prenume[100];
        char Studii[1500];
        char Experienta[1000];
        char Joburi_dorite[560];
        char Abilitati[1500];
        char Descriere[1000];

        char Email[256];
        char Nastere[50];
        int id;
    };


void Construire_CV(){

    FILE * file;
    file = fopen("CV_uri.txt","a");



    char nume_eror[80];
    struct CV cv;

      printf("                        -=Construestruirea CV-ul propriu=-\n\n");
    printf(" Introduceti numele si prenumele : ");
    scanf("%s",nume_eror);
    fflush(stdout);
    fgets(cv.Nume_Prenume, 100, stdin);
    strcat(nume_eror,cv.Nume_Prenume);
    strcpy(cv.Nume_Prenume,nume_eror);
    printf(" Introduceti anul nasterii (zi luna an) : ");
    fflush(stdout);

     fflush(stdout);
fgets(cv.Nastere, 50, stdin);
   printf(" Introduceti email sau alte informatii de contact : ");

    fflush(stdout);
fgets(cv.Email, 256, stdin);
    printf(" Introduceti studiile absolvite sau in curs de absolvire si anii : ");

     fflush(stdout);
     fgets(cv.Studii, 1500, stdin);
    printf(" Introduceti date despre experienta anterioara : ");

        fflush(stdout);
fgets(cv.Experienta, 1500, stdin);
    printf(" Jobul/Joburile pentru care doriti sa candidati :");

   fflush(stdout);
   fgets(cv.Joburi_dorite, 560, stdin);
    printf(" Scrieti despre abilitatile dumneavostra : ");


   fflush(stdout);
   fgets(cv.Abilitati, 1500, stdin);
    printf(" Alte informatii : ");


   fflush(stdout);
   fgets(cv.Descriere, 1500, stdin);

    time_t t;
    srand((unsigned) time(&t));
    cv.id = rand();


    fprintf(file, "id :%d\n Nume:%s Anul Nasterii:%s Contacte:%s Studii:%s Experienta:%s Joburi dorite:%s Abilitati:%s Alte informatii:%s\n", cv.id, cv.Nume_Prenume, cv.Nastere, cv.Email, cv.Studii, cv.Experienta, cv.Joburi_dorite, cv.Abilitati, cv.Descriere);


    printf("  CV-ul a fost salvat id dumnevoastra - %d - pastrati acest id\n",cv.id);
    fclose(file);
    system("pause");
    system("cls");
    inapoi();
}





void Solicitare_Cv(){



   FILE *f = fopen("CV_uri.txt", "rb");
if (f == NULL) {
    printf("Eroare la deschiderea fisierului.");
    return;
}

// Initializam o lista goala
struct CV lista_citita[100]; // presupunem ca nu vom citi mai mult de 100 de structuri
int nr_cv = 0;

// Citim structurile din fisier si le adaugam in lista
while (nr_cv < 200 && fread(&lista_citita[nr_cv], sizeof(struct CV), 1, f) == 1) {
       nr_cv++;
   }

// Inchidem fisierul
fclose(f);
    int  alege_cautare;
    long  id;
    char caut_ocupatie[100];
    int alegere_sol;
    printf("\n                                        -=Solicitarea unui CV dupa id=-\n\n");
    printf(" Alegeti una din optiuni \n");
    printf(" 1<- Cautare dupa id \n");
    printf(" 2<- Stergerea dupa id\n");
    printf(" 3<- Afisarea tuturor CV-urilor\n");
    printf(" 4<- Inapoi\n");
    scanf("%d",&alege_cautare);
    if(alege_cautare==1)
    {printf("Introduceti id : ");
        scanf("%ld",&id);
      for(int i =0;i<100;i++)
        {
            if(lista_citita[i].id == id)
           {
    printf("\nNume si prenume: %s\n", lista_citita[i].Nume_Prenume);
    printf("Studii: %s\n", lista_citita[i].Studii);
    printf("Experienta: %s\n", lista_citita[i].Experienta);
    printf("Joburi dorite: %s\n", lista_citita[i].Joburi_dorite);
    printf("Abilitati: %s\n", lista_citita[i].Abilitati);
    printf("Descriere: %s\n", lista_citita[i].Descriere);
    printf("Email: %s\n", lista_citita[i].Email);
    printf("Data nasterii: %s\n", lista_citita[i].Nastere);
    printf("ID: %d\n", lista_citita[i].id);
}
      }

        printf("1<- Inapoi : \n");
        printf(" Introduceti cifra corespunzatoare : ");
        scanf("%d",&alegere_sol);
      if(alege_cautare == 1)
    {    system("cls");
        inapoi();
    }
    }
    if (alege_cautare == 2)
   {   system("cls");
       Stergere();
   }
 if (alege_cautare == 3) {

    FILE *file = fopen("CV_uri.txt", "r");

        if (file == NULL) {
            printf("Eroare la deschiderea fisierului!\n");
            exit(1);
        }
        char line[1000];
        while (fgets(line, sizeof(line), file)) {
            printf("         %s", line);
        }
        fclose(file);

system("pause");
        system("cls");
    inapoi();



  }
    if(alege_cautare == 4)
    {    system("cls");
        inapoi();
    }
    }




void job_disp() {
    int cautare;
    char job[50];
    printf("1<- Afisarea tuturor joburilor disponibile : \n");
    printf("2<- Inapoi \n");
    scanf("%d",&cautare);

    if (cautare == 1) {
        printf("Lista de joburi:\n");
        FILE* fp;
        fp = fopen("Joburi.txt", "r");
        if (fp == NULL) {
            printf("Eroare la deschiderea fisierului!\n");
            exit(1);
        }
        char line[1000];
        while (fgets(line, sizeof(line), fp)) {
            printf("%s", line);
        }
        fclose(fp);
        printf("\n");
        system("pause");
        system("cls");
        inapoi();
    }
    if (cautare == 2) {
        system("cls");
        inapoi();
    }
}


typedef struct {
    char companie[200];
    char tip_job[1000];
    char cerinte[2000];
    char scurta_descriere[1500];
    char contacte[300];
} OfertaJob;

void Depunere_oferta() {
    char nume_eror[50];
    OfertaJob oferta;
    printf("Introduceti numele companiei: ");

     scanf("%s",nume_eror);
    fflush(stdout);
    fgets(oferta.companie, 200, stdin);

strcat(nume_eror,oferta.companie);
    strcpy(oferta.companie,nume_eror);
    printf("Introduceti tipul jobului: ");

    fflush(stdout);
    fgets(oferta.tip_job, 1000, stdin);
    printf("Cerinte candidat: ");
    fflush(stdout);
    fgets(oferta.cerinte, 2000, stdin);
    printf("Scurta descriere a responsabilitatilor si a facilitatilor: ");
    fflush(stdout);
    fgets(oferta.scurta_descriere, 1500, stdin);
    printf("Date de contact: ");
    fflush(stdout);
    fgets(oferta.contacte, 300, stdin);

    FILE *f = fopen("Joburi.txt", "a");
    if (f == NULL) {
        printf("Eroare la deschiderea fisierului.\n");
        return;
    }

    fprintf(f, "Companie: %sTip job: %sCerinte candidat: %sScurta descriere: %sContacte: %s\n",
            oferta.companie, oferta.tip_job, oferta.cerinte, oferta.scurta_descriere, oferta.contacte);

    fclose(f);
    printf("Oferta a fost cu succes depusa.\n");
    system("pause");
    system("cls");
    inapoi();
}


void Stergere(){
    int id;
    printf(" Introduceti id-ul din CV : ");
    scanf("%d",&id);
//    compararea unui id ddaca exista
    printf("CV-ul %d a fost sters \n",id);
    system("pause");
    system("cls");
    inapoi();

}




void modificare_stergere()
{
    int alegere,id;
    printf("1 <- Pentru modificare ofertei :\n");
    printf("2 <- Pentru stergerea ofertei :\n");
    printf("3 <- Inapoi\n");

    printf("Introduceti cifra corespunzatoare : ");
    scanf("%d",&alegere);
    if(alegere == 1)
    {
        printf("introduceti id : ");
        scanf("%d",&id);
        modificare_Oferta();
        system("pause");
        system("cls");
        inapoi();
    }
    if(alegere == 2)
    { printf("introduceti id : ");
        scanf("%d",&id);
        printf("%d a fost sters \n",id);
        system("pause");
        system("cls");
        inapoi();
    }
    if(alegere == 3)
    {system("cls");
        inapoi();
    }
}



void modificare_Oferta(){
    int cifra;
printf("1<- Pentru modificare numele companiei : \n");
printf("2<- Pentru modificare tipul jobului : \n");
printf("3<- Pentru modificare cerinte candidat : \n");
printf("4<- Pentru modificare descrierea responsabilitati si facilitati : \n");
printf("5<- Pentru modificare date de contact : \n");
printf("Introduceti cifra corespunzatoare : ");
scanf("%d",&cifra);
printf("%d a fost modificat ",cifra);

}

void modificare_CV(){
    int cifra;
printf("1<- Pentru modificare nume : \n");
printf("2<- Pentru modificare anul nasterii  : \n");
printf("3<- Pentru modificare numarul de telefon : \n");
printf("4<- Pentru modificare informatiilor de contact : \n");
printf("5<- Pentru modificare date despre experienta : \n");
printf("6<- Pentru modificare jobului dorit : \n");
printf("7<- Pentru modificare abilitatilor dumneavoastra : \n");
printf("8<- Pentru modificare descrierii : \n");
printf("Introduceti cifra corespunzatoare : ");
scanf("%d",&cifra);
printf("%d a fost modificat ",cifra);

}




void inapoi(){
    int cifra_introdusa;
    Prima_pagina();
    fflush(stdout);
    printf("Introduceti cifra corespunzatoare : ");
    scanf("%d",&cifra_introdusa);

    if(cifra_introdusa < 1 || cifra_introdusa > 5)
        do{ fflush(stdout);
                printf("Introduceti cifra corespunzatoare unei optiuni: ");
            scanf("%d",&cifra_introdusa);

        }while( cifra_introdusa < 1 || cifra_introdusa > 5 );

    system("cls");

    if(cifra_introdusa == 1)
    {
        Construire_CV();

    }

    if(cifra_introdusa == 2)
    {
        Solicitare_Cv();
    }
    if (cifra_introdusa == 3)
    {
        job_disp();
    }
    if (cifra_introdusa == 4)
    {
        Depunere_oferta();
    }
    //if(cifra_introdusa == 5)
   // {
   //     modificare_stergere();
  //  }

    if(cifra_introdusa == 5)
    {
        exit(0);
    }
}
