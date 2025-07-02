#include <stdio.h>
#include <stdlib.h>
#include <stdint.h>
#include <string.h>
#include <time.h>


// Dimensiunea unui bloc folosit în mod CBC 64 biți
#define BLOCK_SIZE 8

// Dimensiunea unui chunk de date citit din fișier (1 MiB)
#define CHUNK_SIZE (1<<20)


// Această funcție face o permutarea inițială S[256] pe baza cheii secrete

void rc4_init(uint8_t *key, int key_len, uint8_t *S) {
    int i, j = 0;

    // Inițializare vector S cu valori de la 0 la 255
    for (i = 0; i < 256; i++) {
        S[i] = i;
    }

    // Permutarea valorilor din S în funcție de cheia secretă
    for (i = 0; i < 256; i++) {
        j = (j + S[i] + key[i % key_len]) & 0xFF;  // limiteaza valorarea 0-255
        uint8_t tmp = S[i];
        S[i] = S[j];
        S[j] = tmp;
    }
}



// RC4 PRGA (Pseudo-Random Generation Algorithm)

// Generează un flux de chei si il aplică prin XOR peste date

void rc4_prga(uint8_t *S, uint8_t *data, uint8_t *out, size_t len) {
    int i = 0, j = 0;

    for (size_t n = 0; n < len; n++) {
        i = (i + 1) & 0xFF;
        j = (j + S[i]) & 0xFF;
        uint8_t tmp = S[i];
        S[i] = S[j];
        S[j] = tmp;

        // Extrage octetul din fluxul de chei și aplică XOR
        
        uint8_t K = S[(S[i] + S[j]) & 0xFF];

        out[n] = data[n] ^ K;
    }
}


int main(int argc, char *argv[]) {
    
    if (argc != 5) {
        fprintf(stderr,
            "Utilizare:\n"
            "  %s -e fisier_input -o fisier_criptat\n"
            "  %s -d fisier_criptat -o fisier_decriptat\n",
            argv[0], argv[0]
        );
        return 1;
    }

    
    int encrypt;
    if      (strcmp(argv[1], "-e") == 0) encrypt = 1;
    else if (strcmp(argv[1], "-d") == 0) encrypt = 0;
    else {
        fprintf(stderr, "Opțiune necunoscută: %s\n", argv[1]);
        return 2;
    }

 
    if (strcmp(argv[3], "-o") != 0) {
        fprintf(stderr, "Se așteaptă -o, ai dat: %s\n", argv[3]);
        return 3;
    }

   
    const char *in_file  = argv[2];
    const char *out_file = argv[4];

    
    FILE *fin  = fopen(in_file,  "rb");
    FILE *fout = fopen(out_file, "wb");
   
    if (!fin)  { perror("Deschidere intrare");  return 4; }
    if (!fout) { perror("Deschidere ieșire");    fclose(fin); return 5; }

   
    fseek(fin, 0, SEEK_END);
    long total_size = ftell(fin);
    fseek(fin, 0, SEEK_SET);

   
    uint8_t key[] = "cheia_secreta";    // Cheia RC4

    int key_len   = strlen((char*)key);  // Lungimea cheii
   
    uint8_t IV[BLOCK_SIZE] = {0x01,0x02,0x03,0x04,0x05,0x06,0x07,0x08};  // IV fix

    
    uint8_t S0[256];

    //permutarea s0 initial 

    rc4_init(key, key_len, S0);

    
    uint8_t *chunk     = malloc(CHUNK_SIZE);     // Date brute citite
    uint8_t *keystream = malloc(CHUNK_SIZE);     // Stare RC4 pentru PRGA

    uint8_t *outchunk  = malloc(CHUNK_SIZE);     // Date criptate/decriptate
    uint8_t prev[BLOCK_SIZE];                    // Blocul precedent pentru mod CBC
    memcpy(prev, IV, BLOCK_SIZE);                // Inițializează cu IV-ul

    
    clock_t t0 = clock();

    // Procesează fișierul în bucăți de CHUNK_SIZE

    size_t read_bytes;

    while ((read_bytes = fread(chunk, 1, CHUNK_SIZE, fin)) > 0) {
        // Clonează starea RC4 inițială pentru acest chunk
        memcpy(keystream, S0, 256);

        // Generează fluxul de chei RC4 și aplică XOR
        rc4_prga(keystream, chunk, outchunk, read_bytes);

        // Aplica modul CBC pe blocuri de BLOCK_SIZE
        for (size_t offset = 0; offset < read_bytes; offset += BLOCK_SIZE) {
           
            int bl = (int)((read_bytes - offset) >= BLOCK_SIZE ? BLOCK_SIZE : (read_bytes - offset));

            if (encrypt) {

                // Modul CBC în criptare: XOR cu blocul precedent și actualizează `prev`
                for (int b = 0; b < bl; b++) {
                    outchunk[offset + b] ^= prev[b];
                    prev[b] = outchunk[offset + b];
                }
            } else {

                // Modul CBC în decriptare: salvează blocul curent, XOR cu `prev`, apoi actualizează `prev`
                uint8_t saved[BLOCK_SIZE];
                memcpy(saved, &chunk[offset], bl);
                for (int b = 0; b < bl; b++) {
                    outchunk[offset + b] ^= prev[b];
                    prev[b] = saved[b];
                }
            }
        }

        fwrite(outchunk, 1, read_bytes, fout);
   
    }

    
    clock_t t1 = clock();
    double elapsed = (double)(t1 - t0) / CLOCKS_PER_SEC;

    printf("%s completă: %ld bytes → %s\n", encrypt ? "Criptare" : "Decriptare", total_size, out_file);
    printf("Timp executie: %.3f secunde\n", elapsed);

   
    free(chunk);
    free(keystream);
    free(outchunk);

    fclose(fin);
    fclose(fout);

    return 0;
}


//gcc rc4.c -o rc4

// ./rc4 -d cripted.txt -o result.txt 
// ./rc4 -e plain.txt -o cripted.txt