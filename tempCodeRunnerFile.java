BufferedWriter writer = new BufferedWriter(new FileWriter("uploaded_text.txt"));
                String line;
                while (!(line = in.readLine()).equals("END")) {
                    writer.write(line);
                    writer.newLine();
                }
                writer.close();
                out.println("File uploaded successfully");