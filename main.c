#include <stdio.h>
#include "Meniu.h"
#include <stdlib.h>
#include <string.h>
#include <time.h>
int main(int argc, const char * argv[]) {

    int cifra_introdusa;
    Prima_pagina();

    printf("Introduceti cifra corespunzatoare : ");

    scanf("%d",&cifra_introdusa);

    if(cifra_introdusa < 1 || cifra_introdusa > 5)
        do{
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
    //if (cifra_introdusa == 5)
   // {
   //     modificare_stergere();
  //  }
    if(cifra_introdusa == 5)
    {
        exit(0);
    }

    return 0;
}
