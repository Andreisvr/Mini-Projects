#include <stdio.h>
#include <stdlib.h>
#include <string.h>




// Funcție pentru exponentiere modulară: (text^exp) % mod sau decriptare : text_cripted^d mod n
// prin pătrățirea repetată
// pentru a preveni crearea numerelor foarte mari care nu încap în memorie.
//daca e = 13 => base ^13 = base ^8⋅base ^4⋅base ^1

//calculeaza caracter^e mod n

//Dacă exp este impar (adică ultimul bit e 1), înmulțim rezultatul cu base.
//Apoi împărțim exp la 2 (shift la dreapta, exp >>= 1).
//Ridicăm base la pătrat (modulo mod).

unsigned long long mod_exp(unsigned long long base, unsigned long long exp, unsigned long long n) {
   
    unsigned long long result = 1;  
    base %= n;                    // Reducem base modulo mod pentru a preveni depășirea
   
    while (exp > 0) {
        if (exp % 2 == 1)            // Dacă exponentul este impar
            result = (result * base) % n;  // Înmulțim cu base (mod mod)
       
            exp >>= 1;                   
        base = (base * base) % n; 
    }
    return result; 
}

// Funcție pentru citirea cheii dintr-un fișier
// e n sau d n 
int read_key(const char *filename, unsigned long long *exp, unsigned long long *n) {
   
    FILE *f = fopen(filename, "r"); 
    if (!f) {
        perror("Nu se poate deschide fișierul de chei"); 
        return 0;  
    }
    
    fscanf(f, "%llu %llu", exp, n);  
    fclose(f); 
    return 1;  
}


// Fiecare caracter din fișierul de intrare va fi criptat folosind cheia publică (exp, n)

void rsa_encrypt(const char *input_file, const char *output_file, unsigned long long e, unsigned long long n) {
   
    FILE *in = fopen(input_file, "rb");  
    FILE *out = fopen(output_file, "w"); 
   
    if (!in || !out) {
        perror("Eroare la deschiderea fișierelor");  
    
        exit(1);  
    }

    int ch;
    
    while ((ch = fgetc(in)) != EOF) {
     
        // Criptăm fiecare caracter folosind exponentiere modulară
        unsigned long long encrypted = mod_exp((unsigned long long)ch, e, n);
       
        fprintf(out, "%llu ", encrypted);
    }

    fclose(in); 
  
    fclose(out); 
}


// Fiecare număr criptat din fișierul de intrare va fi decriptat folosind cheia privată (d, n)

void rsa_decrypt(const char *input_file, const char *output_file, unsigned long long d, unsigned long long n) {
    
    FILE *in = fopen(input_file, "r");   
    FILE *out = fopen(output_file, "wb");
   
    if (!in || !out) {
        perror("Eroare la deschiderea fișierelor"); 
        exit(1); 
    }

    unsigned long long encrypted;
   
    while (fscanf(in, "%llu", &encrypted) != EOF) {
        
    // Decriptăm numărul criptat folosind exponentiere modulară
        
        unsigned char decrypted = (unsigned char)mod_exp(encrypted, d, n);
        
        fputc(decrypted, out);
    }

    fclose(in);  
    fclose(out);

}

int main(int argc, char *argv[]) {
  
    if (argc != 7 || strcmp(argv[3], "-k") != 0 || strcmp(argv[5], "-o") != 0) {
        fprintf(stderr, "Utilizare: rsa -e|-d input.txt -k keyfile.txt -o output.txt\n");
        return 1;  
    }

    const char *mode = argv[1];            
    const char *input_file = argv[2];     
    const char *key_file = argv[4];       
    const char *output_file = argv[6];    

    unsigned long long exp, n;
    
    if (!read_key(key_file, &exp, &n)) return 1;

    
   
    if (strcmp(mode, "-e") == 0) {

        rsa_encrypt(input_file, output_file, exp, n); 

    } else if (strcmp(mode, "-d") == 0) {

        rsa_decrypt(input_file, output_file, exp, n); 

    } else {

        fprintf(stderr, "Mod necunoscut: %s (folosește -e sau -d)\n", mode);
        return 1; 
    }

    printf("Operația s-a finalizat cu succes.\n"); 
    return 0;  
    
}


// ./rsa -e plain.txt -k key_pb.txt -o cripted.txt 

// ./rsa -d cripted.txt -k key_priv.txt -o result.txt