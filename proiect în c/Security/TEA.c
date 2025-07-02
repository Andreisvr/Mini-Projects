#include <stdio.h>
#include <stdlib.h>
#include <stdint.h>
#include <string.h>
#include <time.h>


// Constanta DELTA utilizată în algoritmul TEA (Tiny Encryption Algorithm)
#define DELTA 0x9E3779B9 //derivata din numarul de aur 
#define NUM_ROUNDS 32         // Numărul de runde standard pentru TEA
#define BLOCK_SIZE 8      //64 biti   


typedef struct {
   
    uint32_t v0;
    uint32_t v1;

} Block;

// Funcția de criptare folosind algoritmul TEA
Block encrypt_tea(Block v, uint32_t key[4]) {
    
    uint32_t sum = 0;
    for (int i = 0; i < NUM_ROUNDS; i++) {
        sum += DELTA;  // Se adaugă DELTA la fiecare rundă
        v.v0 += ((v.v1 << 4) + key[0]) ^ (v.v1 + sum) ^ ((v.v1 >> 5) + key[1]);
        v.v1 += ((v.v0 << 4) + key[2]) ^ (v.v0 + sum) ^ ((v.v0 >> 5) + key[3]);
    }
    return v;
}

// Funcția de decriptare folosind algoritmul TEA
Block decrypt_tea(Block v, uint32_t key[4]) {
   
    uint32_t sum = DELTA * NUM_ROUNDS;  // Se pornește cu suma maximă
    for (int i = 0; i < NUM_ROUNDS; i++) {
       
        v.v1 -= ((v.v0 << 4) + key[2]) ^ (v.v0 + sum) ^ ((v.v0 >> 5) + key[3]);
        v.v0 -= ((v.v1 << 4) + key[0]) ^ (v.v1 + sum) ^ ((v.v1 >> 5) + key[1]);
       
        sum -= DELTA;  // Se scade DELTA la fiecare rundă
    }
    return v;

}

// Criptare în modul CBC (Cipher Block Chaining) cripted = Ecncript(Text[i] xor cripted[i-1])

void encrypt_cbc(Block* input, Block* output, int num_blocks, uint32_t key[4], Block iv) {
   
    Block prev = iv;

    for (int i = 0; i < num_blocks; i++) {
        // XOR cu blocul anterior
        input[i].v0 ^= prev.v0;
        input[i].v1 ^= prev.v1;

      
        Block cipher = encrypt_tea(input[i], key);
        output[i] = cipher;

        
        prev = cipher;
    }

}

// Decriptare în modul CBC (Cipher Block Chaining)
void decrypt_cbc(Block* input, Block* output, int num_blocks, uint32_t key[4], Block iv) {
    
    Block prev = iv;
    for (int i = 0; i < num_blocks; i++) {
        // Se decriptează blocul
       
        Block decrypted = decrypt_tea(input[i], key);

        // XOR cu blocul anterior (sau IV pentru primul)
        output[i].v0 = decrypted.v0 ^ prev.v0;
        output[i].v1 = decrypted.v1 ^ prev.v1;

        // Se actualizează „prev” pentru runda următoare
        prev = input[i];
    }
}


int main(int argc, char* argv[]) {
    
    if (argc != 5) {
        printf("Utilizare:\n");
        printf("  %s -e fisier_input -o fisier_criptat\n", argv[0]);
        printf("  %s -d fisier_criptat -o fisier_decriptat\n", argv[0]);
        return 1;
    }

    int encrypt = 0;
    const char* input_file = NULL;
    const char* output_file = NULL;


    if (strcmp(argv[1], "-e") == 0) {
        encrypt = 1;
        input_file = argv[2];
    } else if (strcmp(argv[1], "-d") == 0) {
        encrypt = 0;
        input_file = argv[2];
    } else {
        
    fprintf(stderr, "Eroare: opțiune necunoscută %s\n", argv[1]);
    return 1;
    
    }

    
    if (strcmp(argv[3], "-o") != 0) {
        fprintf(stderr, "Eroare: se aștepta opțiunea -o\n");
        return 1;
    }
    output_file = argv[4];

    
    FILE* fin = fopen(input_file, "rb");
    if (!fin) {
        perror("Eroare la deschiderea fișierului de intrare");
        return 1;
    }

    // Obține dimensiunea fișierului
    fseek(fin, 0, SEEK_END);
    long file_size = ftell(fin);
    fseek(fin, 0, SEEK_SET);

    // Calculează numărul de blocuri de 8 octeți (64 biți), rotunjind în sus
    long num_blocks = (file_size + 7) / 8;

    // Alocă memorie pentru blocurile de intrare și ieșire
    Block* in_blocks = calloc(num_blocks, sizeof(Block));
    Block* out_blocks = calloc(num_blocks, sizeof(Block));

    // Citește datele binare din fișierul de intrare
    fread(in_blocks, BLOCK_SIZE, num_blocks, fin);
    fclose(fin);

    // Cheia de criptare formată din 4 cuvinte de 32 biți
    uint32_t key[4] = {0x0001E0F8, 0x00039270, 0x00052A80, 0x00061A80};

    // Vectorul (IV) pentru modul CBC
    Block iv = {0x000007C7, 0x000007C8};

   
    clock_t start = clock();

    if (encrypt) {
        encrypt_cbc(in_blocks, out_blocks, num_blocks, key, iv);
        printf("Criptare completă ");
    } else {
        decrypt_cbc(in_blocks, out_blocks, num_blocks, key, iv);
        printf("Decriptare completă ");
    }

    clock_t end = clock();
    double elapsed = (double)(end - start) / CLOCKS_PER_SEC;
    printf("în %.5f secunde.\n", elapsed);

    
    FILE* fout = fopen(output_file, "wb");
    if (!fout) {
        perror("Eroare la deschiderea fișierului de ieșire");
        free(in_blocks);
        free(out_blocks);
        return 1;
    }

    fwrite(out_blocks, BLOCK_SIZE, num_blocks, fout);
    fclose(fout);

   
    free(in_blocks);
    free(out_blocks);
    return 0;
}


// ./tea -e plain.txt -o cripted.txt 
// ./tea -d cripted.txt -o result.txt
// gcc tea.c -o tea     