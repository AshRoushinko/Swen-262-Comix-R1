package model.parsefiles;

public class ParseTitle implements ParseOperationState{
    @Override
    public void nextPOS(Parser parser) {
        parser.setState(new ParseDescription());
    }

    /**
     * Purpose - to get the title from the line
     * @param line - The current line
     * @param idx - the current idx
     * @return - returns a ParseInfo object containing the title and the updated line index
     */
    @Override
    public ParseData parseValue(String line, int idx) {
        String titleGet;//Creates an empty string that will be initialized with the title and returned
        int titleEnd;//Declares the integer that will represent the index following the end of the title.
        //Check for no title
        if (line.startsWith(",")){
            titleGet = "";//Set title to an empty string
            idx+=1;//Offset the idx by one for the comma
        }
        else if (line.startsWith("\"")){//Check for quotations
            if(line.contains("\"\"")){//Check for embedded quotations (Checks whole line not just the title)
                if(line.startsWith("\"\"\"")){//Check for entire title in embedded quotations
                    if(line.substring(3).contains("\"\"\",")){//Check to see if it ends with triple quotations
                        titleEnd = line.indexOf("\"\"\",");//Get the index of the triple quotations followed by the comma (""",) which represents the end of the title
                        String titleGetTemp = line.substring(1,titleEnd+2);//Set the title (Still includes double quotes)(start index is one to remove quotations)
                        titleGet = titleGetTemp.replaceAll("\"\"", "\"");//Replace all double quotes with singe quotes
                        idx+=titleEnd+4;//Add the index of the end of the title to the current index (add four for quotations and comma)
                    }
                    else{//Doesn't end in triple quotations
                        //FIND INDEX OF "" and then search for ", after
                        titleEnd = line.indexOf(",\"");//Get the index of the comma followed by a single quote (,") which represents the end of the title
                        String titleGetTemp = line.substring(1,titleEnd-1);//Set the title (Still includes double quotes)(start index is one to remove quotations)
                        titleGet = titleGetTemp.replaceAll("\"\"", "\"");//Replace all double quotes with singe quotes
                        idx+=titleEnd+1;//Add the index of the end of the title to the current index (add one for comma)
                    }
                }
                else{//embedded quotes are included but not in first position (first position always has single quote)
                    if(line.substring(1).contains("\"\"\",")){//Check to see if it ends with triple quotations
                        titleEnd = line.indexOf("\"\"\",");//Get the index of the triple quotations followed by the comma (""",) which represents the end of the title
                        String titleGetTemp = line.substring(1,titleEnd+2);//Set the title (Still includes double quotes)(start index is one to remove quotations)
                        titleGet = titleGetTemp.replaceAll("\"\"", "\"");//Replace all double quotes with singe quotes
                        idx+=titleEnd+4;////Add the index of the end of the title to the current index (add four for quotes and comma)
                    }
                    else{//Doesn't end with triple quotations
                        if(line.indexOf("\",")<line.indexOf("\"\"")){//This checks to see if the double quotes are after the end of the title (No embedded quotes)
                            titleEnd = line.indexOf("\",");//Gets the index of the quotation followed by the comma (",) which represents the end of the title
                            titleGet = line.substring(1,titleEnd);//Set the title (start index is one to remove quotation)
                            idx+=titleEnd+2;//Add the index of the end of the title to the current index (add two for quotations and comma)
                        }
                        else{
                            if (line.substring(1).contains("\"\",")){//Need to look for (",) which is end of the title: ("",) will show up too so if the line has it
                                //the search for (",) will continue after (can be multiple ("",))
                                int lastDoubleQuoteC = line.lastIndexOf("\"\",")+3;//Gets the last index of the ("",) in the line (add by three to offset ("",))
                                int titleEndTemp = line.substring(lastDoubleQuoteC).indexOf("\",");//Gets the index of (",) from the last instance of ("",)
                                titleEnd = lastDoubleQuoteC+titleEndTemp;//Gets the real index of the (",) following the end of the title
                                String titleGetTemp = line.substring(1,titleEnd);//Set the title (start index is one to remove quotation)(Still includes double quotes)
                                titleGet = titleGetTemp.replaceAll("\"\"", "\"");//Replace all double quotes with singe quotes
                                idx+=titleEnd+2;//Add the index of the end of the title to the current index (add two for quotations and comma)
                            }
                            else{//Ends with (",) and has any embedded quotes
                                titleEnd = line.indexOf("\",");//Get the index of the quotation followed by the comma (",) which represents the end of the title
                                String titleGetTemp = line.substring(1,titleEnd);//Set the title (start index is one to remove quotation)(Still includes double quotes)
                                titleGet = titleGetTemp.replaceAll("\"\"", "\"");//Replace all double quotes with singe quotes
                                idx+=titleEnd+2;//Add the index of the end of the title to the current index (add two for quotations and comma)
                            }
                        }
                    }
                }
            }
            else{//If there is no embedded quotations
                titleEnd = line.indexOf("\",");//Get the index of the quotation followed by the comma (",) which represents the end of the title
                titleGet = line.substring(1,titleEnd);//Set the title (start index is one to remove quotation)
                idx+=titleEnd+2;//Add the index of the end of the title to the current index (add two for quotations and comma)
            }
        }
        //Handles any Title without parenthesis
        else{
            titleEnd = line.indexOf(",");//Gets the index of the comma following the title
            titleGet = line.substring(0,titleEnd);//Generates the title
            idx+=titleEnd+1;//Add the index of the end of the title to the current index (add one for comma)
        }
        return new ParseData(idx, titleGet);//Return a parse info object containing the parse information for the title
    }

    @Override
    public Boolean hasNextState() {
        return true;
    }
}
