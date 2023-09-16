#include <stdio.h>
#include <iostream>
#include <vector>
#include <map>
#include <fstream>
#include <string>
#include <cstring>
#include <sstream>

using namespace std;
 //   categorii listaProduse;
// Modificare pret , Stergere cantitate, adaugare in stoc , 
//===========================Modificarea pret
void modificarePret(int cod_bare, int pret_nou) {
    const string nume_fisier = "stoc.txt";
    ifstream fisier(nume_fisier);
    string linie;
    vector<string> linii_actualizate;

    while (getline(fisier, linie)) {
        istringstream iss(linie);
        string categorie, cod_bare_str, nume, stoc_str, pret_str, caracteristici;
        getline(iss, categorie, ',');
        getline(iss, cod_bare_str, ',');
        getline(iss, nume, ',');
        getline(iss, stoc_str, ',');
        getline(iss, pret_str, ',');
        getline(iss, caracteristici);

        int cod_bare_actual = stoi(cod_bare_str);
        int pret_actual = stoi(pret_str);

        if (cod_bare_actual == cod_bare) {
            pret_actual = pret_nou;
        }

        stringstream ss;
        ss << categorie << "," << cod_bare_actual << "," << nume << "," << stoc_str << "," << pret_actual << "," << caracteristici;
        linii_actualizate.push_back(ss.str());
    }

    fisier.close();

    ofstream fisier_actualizat(nume_fisier);
    for (const auto& linie_actualizata : linii_actualizate) {
        fisier_actualizat << linie_actualizata << endl;
    }
    fisier_actualizat.close();

    cout << "Pretul pentru codul de bare " << cod_bare << " a fost actualizat cu succes." << endl;
}
//========================Stergerea

void stergeProdusDinStoc(long int cod_bare, int cantitate) {
    ifstream fin("/Users/Andrei_Sviridov/Desktop/Magaz/Magaz/magaz/magaz/Stoc_adaugare.txt");
    if (!fin) {
        cerr << "Nu s-a putut deschide fișierul pentru citire!" << endl;
        return;
    }

    vector<string> lines;
    string line;

    while (getline(fin, line)) {
        string codBareStr = line.substr(line.find(",") + 1);
        long int codBareLinie;
        try {
            codBareLinie = stol(codBareStr);
        } catch (const std::invalid_argument& e) {
            cerr << "Invalid argument: " << e.what() << endl;
            fin.close();
            return;
        } catch (const std::out_of_range& e) {
            cerr << "Out of range: " << e.what() << endl;
            fin.close();
            return;
        }
        if (codBareLinie == cod_bare) {
            std::istringstream iss(line);
            std::string token;
            std::getline(iss, token, ',');  // Skip the first argument
            std::getline(iss, token, ',');
            std::getline(iss, token, ',');  // Skip the third argument
            std::getline(iss, token, ',');

            int stoc = std::stoi(token);
            if (stoc == cantitate) {
                // Produsul are cantitatea dorită, nu adăugăm linia în vector
                continue; // Pentru a trece la următoarea iterație a buclei while
            } else if (stoc > cantitate) {
                // Calculează diferența dintre stoc și cantitate
                int diferenta = stoc - cantitate;

                if (diferenta == 0) {
                    // Șterge întreaga linie din vectorul lines
                    continue; // Pentru a trece la următoarea iterație a buclei while
                } else {
                    // Actualizează cantitatea în linie
                    line = line.substr(0, line.rfind(",") + 1) + to_string(diferenta);
                }
            } else {
                cerr << "Cantitatea introdusă este mai mare decât stocul disponibil pentru produsul selectat." << endl;
                fin.close();
                return;
            }
        }

        lines.push_back(line);
    }

    fin.close();

    ofstream fout("Stoc_actualizat.txt");
    if (!fout) {
        cerr << "Nu s-a putut deschide fișierul pentru scriere!" << endl;
        return;
    }

    for (const string& line : lines) {
        fout << line << endl;
    }

    fout.close();

    cout << "Produsul a fost șters din stoc." << endl;
}

//============================Adaugare

void adaugaProdusInStoc(const char* fisier, const char* categorie, long int cod_bare, const char* nume, int stoc, int pret, const char* caracteristici) {
    ofstream fout(fisier, ios::app);
    if (!fout) {
        cerr << "Nu s-a putut deschide fișierul pentru scriere!" << endl;
        return;
    }
    
    fout << categorie << "," << cod_bare << "," << nume << "," << stoc << "," << pret << "," << caracteristici << endl;
    
    fout.close();
    
    cout << "Produsul a fost adăugat în stoc." << endl;
    
}

//============================Vizualizare
void vizualizare()
{
    categorii listaProduse;
    ifstream fin("Stoc_adaugare.txt");
    if (!fin) {
        cerr << "Nu s-a putut deschide fisierul!" << endl;
        return ;
    }
    
   // long int cod_bare;
    string nume;
  //  int stoc;
  //  int pret;
    string caracteristici;
    string categorie;

    while (getline(fin >> ws, categorie, ',')) {
        std::string cod_bare_str, nume, stoc_str, pret_str, caracteristici;
        
        // Citeste valorile separate prin virgule
        getline(fin >> ws, cod_bare_str, ',');
        getline(fin >> ws, nume, ',');
        getline(fin >> ws, stoc_str, ',');
        getline(fin >> ws, pret_str, ',');
        
        // Convertește stringurile în valorile adecvate
        long int cod_bare = std::stol(cod_bare_str);
        int stoc = std::stoi(stoc_str);
        int pret = std::stoi(pret_str);
        
        // Citeste linia restanta
        getline(fin >> ws, caracteristici);
        
        // Elimina ghilimelele din caracteristici
        caracteristici = caracteristici.substr(1, caracteristici.length() - 2);
        
        produs p(cod_bare, nume, stoc, pret, caracteristici);
        listaProduse.adaugaProdus(p, categorie);
        
        listaProduse.afisProduseCategorie(categorie);
        p.afisare();
        cout<<endl;
        
        
    }
    
    
    fin.close();
}
