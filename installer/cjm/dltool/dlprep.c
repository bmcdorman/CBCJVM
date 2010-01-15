#include <string.h>
#include <stdio.h>

int main(int argc, char* argv[])
{
    if(argc < 1)
    {
        printf("Insufficeint arguments. Please do not run this manually!");
        return 1;
    }
    char buffer[1024];
    strcpy(buffer, "rm -Rf \"/mnt/user/code/");
    strcat(buffer, argv[1]);
    strcat(buffer, "\"");
    system(buffer);
    strcpy(buffer, "mkdir -p \"/mnt/user/code/");
    strcat(buffer, argv[1]);
    strcat(buffer, "/bin");
    strcat(buffer, "\"");
    system(buffer);
    return 0;
}
