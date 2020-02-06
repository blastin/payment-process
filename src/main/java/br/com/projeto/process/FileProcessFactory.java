package br.com.projeto.process;

import br.com.projeto.payment.Payments;

import java.util.Arrays;

/**
 * @author jefferson
 */
public final class FileProcessFactory {

    public static FileProcess get(final String fileName, final Payments payments) {

        final FileProcess instance =
                Arrays
                        .stream(TypeFile.values())
                        .filter(typeFile -> fileName.matches(typeFile.regex))
                        .findFirst()
                        .orElseThrow(() -> new RuntimeException("Type File not found"))
                        .get(payments);

        return new FileProcessProxy(instance);

    }

    private FileProcessFactory() {
    }

    private enum TypeFile {

        JSON(".*.(json)$") {
            @Override
            FileProcess get(final Payments payments) {
                return new JsonProcess(payments);
            }
        },
        CSV(".*.(csv)$") {
            @Override
            FileProcess get(final Payments payments) {
                return new SVProcess(payments, ",");
            }
        },
        TSV(".*.(tsv)$") {
            @Override
            FileProcess get(final Payments payments) {
                return new SVProcess(payments, "\t");
            }
        },
        XML(".*.(xml)$") {
            @Override
            FileProcess get(final Payments payments) {
                return new XMLProcess(payments);
            }
        };

        private final String regex;

        TypeFile(final String regex) {
            this.regex = regex;
        }

        abstract FileProcess get(final Payments payments);

    }

}
