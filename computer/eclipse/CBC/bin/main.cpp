#include <iostream>
#include <string>
#include <fstream>
#include <stdint.h>
#include "replacements.h"

#ifdef __linux
#include <sys/types.h>
#include <fcntl.h>
#include <unistd.h>
#endif

#define VERSION 1.0

std::string replaceLine(std::string line)
{
    for (uint16_t it = 0; it < NUM_ENTRIES * 2; it += 2)
    {
        size_t loc = 0;
        if ((loc = line.find(replacements[it], loc)) != std::string::npos)
        {
            line.replace(loc, std::string(replacements[it]).length(), replacements[it + 1]);
        }
    }
    return line;
}

void usage()
{
	printf("kissc2cbcjava [c_input_file]");
}

int main(int argc, char* argv[])
{
    printf("kissc2cbclua version %.02f\n", VERSION);
    if (argc > 1)
    {
    	std::string obuffer;
        std::ifstream input;
        input.open(argv[1], std::ios::in);
        if (!input.is_open())
        {
            printf("Unable to open %s!\n", argv[1]);
            return 0;
        }
        while (!input.eof())
        {
            char buffer[1024];
            input.getline(buffer, 1024);
            obuffer += replaceLine(buffer) + std::string("\n");
        }
        std::ofstream output;
        output.open("Main.java", std::ios::out);
        if(!output.is_open())
        {
        	printf("Unable to create output file %s!\n", "Main.java");
        }
        output.write(obuffer.c_str(), obuffer.length());
        return 0;
    }
    else
    {
    	usage();
    }
    return 0;
}
