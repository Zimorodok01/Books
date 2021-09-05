# Requests
    http://localhost:8081/api/v1/books

## Save book
Endpoint: /

Method: POST

    Request
        title:The Pelican Brief
        description:The Pelican Brief is a legal-suspense thriller by John Grisham, published in 1992 by Doubleday. It is his third novel after A Time to Kill and The Firm. Two paperback editions were published, both by Dell Publishing in 1993. A namesake film adaptation was released in 1993 starring Julia Roberts and Denzel Washington.
        author:John Grisham
        isbn:0-385-42198-2
        printYear:1992
        image: <> //selected files

    Response
        {
            "title": "The Pelican Brief",
            "description": "The Pelican Brief is a legal-suspense thriller by John Grisham, published in 1992 by Doubleday. It is his third novel after A Time to Kill and The Firm. Two paperback editions were published, both by Dell Publishing in 1993. A namesake film adaptation was released in 1993 starring Julia Roberts and Denzel Washington.",
            "author": "John Grisham",
            "isbn": "0-385-42198-2",
            "printYear": 1992
        }

## Update book 
Endpoint: /{bookId}

Method: PUT

    Request
        title:Pelican Brief
        description:The Pelican Brief is a legal-suspense thriller by J.Grisham, published in 1993 by Doubleday. It is his third novel after A Time to Kill and The Firm. Two paperback editions were published, both by Dell Publishing in 1993. A namesake film adaptation was released in 1993 starring Julia Roberts and Denzel Washington.
        isbn:0-625-42398-9
        printYear:1993
        image: <> //selected files
    All parameters are not required, so you can omit some of them. For example:
        printYear:1993

    Response
        {
            "title": "Pelican Brief",
            "description": "The Pelican Brief is a legal-suspense thriller by J.Grisham, published in 1993 by Doubleday. It is his third novel after A Time to Kill and The Firm. Two paperback editions were published, both by Dell Publishing in 1993. A namesake film adaptation was released in 1993 starring Julia Roberts and Denzel Washington.",
            "author": "John Grisham",
            "isbn": "0-625-42398-9",
            "printYear": 1993
        }

## Read book
Endpoint: /{bookId}/read

Method: GET

    Response
        Book is already read
            or
        Book is read

## Get Book list
Endpoint: /

Method: GET

    Response
        [
            {
                "title": "The Pelican Brief",
                "description": "The Pelican Brief is a legal-suspense thriller by John Grisham, published in 1992 by Doubleday. It is his third novel after A Time to Kill and The Firm. Two paperback editions were published, both by Dell Publishing in 1993. A namesake film adaptation was released in 1993 starring Julia Roberts and Denzel Washington.",
                "author": "John Grisham",
                "isbn": "0-385-42198-2",
                "printYear": 1992
            },
            {
                "title": "The Last Wish",
                "description": "The Last Wish (Polish: Ostatnie życzenie) is the first (in its fictional chronology; published second in original Polish) of the two collections of short stories (the other being Sword of Destiny) preceding the main Witcher Saga, written by Polish fantasy writer Andrzej Sapkowski. The first Polish edition was published in 1993 and the first English edition was first published in 2007. The book has also been translated into several other languages.",
                "author": "Andrzej Sapkowski",
                "isbn": "978-0-575-08244-1",
                "printYear": 1993
            }
        ]

## Get books by page
Endpoint: /page/{pageNumber}  // page should be in range[1;⌈size/10⌉]

Method: GET

    Response
    the first page
        [
            {
                "title": "The Pelican Brief",
                "description": "The Pelican Brief is a legal-suspense thriller by John Grisham, published in 1992 by Doubleday. It is his third novel after A Time to Kill and The Firm. Two paperback editions were published, both by Dell Publishing in 1993. A namesake film adaptation was released in 1993 starring Julia Roberts and Denzel Washington.",
                "author": "John Grisham",
                "isbn": "0-385-42198-2",
                "printYear": 1992
            },
            {
                "title": "The Last Wish",
                "description": "The Last Wish (Polish: Ostatnie życzenie) is the first (in its fictional chronology; published second in original Polish) of the two collections of short stories (the other being Sword of Destiny) preceding the main Witcher Saga, written by Polish fantasy writer Andrzej Sapkowski. The first Polish edition was published in 1993 and the first English edition was first published in 2007. The book has also been translated into several other languages.",
                "author": "Andrzej Sapkowski",
                "isbn": "978-0-575-08244-1",
                "printYear": 1993
            },
            {
                "title": "The Call of Cthulhu",
                "description": "\"The Call of Cthulhu\" is a short story by American writer H. P. Lovecraft. Written in the summer of 1926, it was first published in the pulp magazine Weird Tales in February 1928",
                "author": "H. P. Lovecraft",
                "isbn": "98-0-574-08544-1",
                "printYear": 1928
            },
            {
                "title": "The Fellowship of the Ring",
                "description": "The Fellowship of the Ring is the first of three volumes of the epic novel The Lord of the Rings by the English author J. R. R. Tolkien. It is followed by The Two Towers and The Return of the King. It takes place in the fictional universe of Middle-earth. It was originally published on 29 July 1954 in the United Kingdom.",
                "author": "J. R. R. Tolkien",
                "isbn": "8-12-574-32544-1",
                "printYear": 1954
            },
            {
                "title": "A Game of Thrones",
                "description": "A Game of Thrones is the first novel in A Song of Ice and Fire, a series of fantasy novels by the American author George R. R. Martin. It was first published on August 1, 1996. The novel won the 1997 Locus Award and was nominated for both the 1997 Nebula Award and the 1997 World Fantasy Award. The novella Blood of the Dragon, comprising the Daenerys Targaryen chapters from the novel, won the 1997 Hugo Award for Best Novella. In January 2011, the novel became a New York Times Bestseller and reached No. 1 on the list in July 2011.",
                "author": "George R. R. Martin",
                "isbn": "0-553-10354-7",
                "printYear": 1996
            },
            {
                "title": "Fight Club ",
                "description": "Fight Club is a 1996 novel by Chuck Palahniuk. It follows the experiences of an unnamed protagonist struggling with insomnia. Inspired by his doctor's exasperated remark that insomnia is not suffering, the protagonist finds relief by impersonating a seriously ill person in several support groups. Then he meets a mysterious man named Tyler Durden and establishes an underground fighting club as radical psychotherapy.",
                "author": "Chuck Palahniuk",
                "isbn": "0-393-03976-5",
                "printYear": 1996
            },
            {
                "title": "1984",
                "description": "Nineteen Eighty-Four, often referred to as 1984, is a dystopian social science fiction novel by the English novelist George Orwell. It was published on 8 June 1949 by Secker & Warburg as Orwell's ninth and final book completed in his lifetime. Thematically, Nineteen Eighty-Four centres on the consequences of totalitarianism, mass surveillance, and repressive regimentation of persons and behaviours within society. Orwell, himself a democratic socialist, modelled the totalitarian government in the novel after Stalinist Russia and Nazi Germany. More broadly, the novel examines the role of truth and facts within politics and the ways in which they are manipulated",
                "author": "George Orwell",
                "isbn": "2-233-36529-8",
                "printYear": 1949
            },
            {
                "title": "Harry Potter and the Philosopher's Stone",
                "description": "Harry Potter and the Philosopher's Stone is a fantasy novel written by British author J. K. Rowling. The first novel in the Harry Potter series and Rowling's debut novel, it follows Harry Potter, a young wizard who discovers his magical heritage on his eleventh birthday, when he receives a letter of acceptance to Hogwarts School of Witchcraft and Wizardry. Harry makes close friends and a few enemies during his first year at the school, and with the help of his friends, he faces an attempted comeback by the dark wizard Lord Voldemort, who killed Harry's parents, but failed to kill Harry when he was just 15 months old.",
                "author": "J. K. Rowling",
                "isbn": "0-7475-3269-9",
                "printYear": 1997
            },
            {
                "title": "The Girl with the Dragon Tattoo ",
                "description": "The Girl with the Dragon Tattoo (original title in Swedish: Män som hatar kvinnor, lit. 'Men Who Hate Women') is a psychological thriller novel by Swedish author and journalist Stieg Larsson (1954–2004), which was published posthumously in 2005 to become an international bestseller. It is the first book of the Millennium series.",
                "author": "Stieg Larsson",
                "isbn": "978-91-1-301408-1",
                "printYear": 2005
            },
            {
                "title": "it",
                "description": "It is a 1986 horror novel by American author Stephen King. It was his 22nd book and his 17th novel written under his own name. The story follows the experiences of seven children as they are terrorized by an evil entity that exploits the fears of its victims to disguise itself while hunting its prey. \"It\" primarily appears in the form of Pennywise the Dancing Clown to attract its preferred prey of young children.",
                "author": "Stephen King",
                "isbn": "0-670-81302-8",
                "printYear": 1996
            }
        ]
    the last page
        [
            {
                "title": "Goldfinger ",
                "description": "Goldfinger is the seventh novel in Ian Fleming's James Bond series. Written in January and February 1958, it was first published in the UK by Jonathan Cape on 23 March 1959. The story centres on the investigation by the British Secret Service operative James Bond into the gold smuggling activities of Auric Goldfinger, who is also suspected by MI6 of being connected to SMERSH, the Soviet counter-intelligence organisation. As well as establishing the background to the smuggling operation, Bond uncovers a much larger plot: Goldfinger plans to steal the gold reserves of the United States from Fort Knox.",
                "author": "Ian Fleming",
                "isbn": "45-090-878034-8",
                "printYear": 1959
            },
            {
                "title": "The Bourne Identity",
                "description": "The Bourne Identity is a 1980 spy fiction thriller by Robert Ludlum that tells the story of Jason Bourne, a man with remarkable survival abilities who has retrograde amnesia, and must seek to discover his true identity. In the process, he must also determine why several shadowy groups, a professional assassin, and the CIA want him dead. It is the first novel of the original Bourne Trilogy, which also includes The Bourne Supremacy and The Bourne Ultimatum.",
                "author": " Robert Ludlum",
                "isbn": "0-399-90070-5",
                "printYear": 1980
            },
            {
                "title": "Red Dragon",
                "description": "Red Dragon is a novel by American author Thomas Harris, first published in 1981. The plot follows former FBI profiler Will Graham, who comes out of retirement to find and apprehend an enigmatic serial-killer nicknamed \"The Tooth Fairy\". The novel introduced the character Dr. Hannibal Lecter, a brilliant psychiatrist and cannibalistic serial-killer, whom Graham reluctantly turns to for advice and with whom he has a dark past. The title refers to the figure from William Blake's painting The Great Red Dragon and the Woman Clothed in Sun.",
                "author": "Thomas Harris",
                "isbn": "0-399-12442-X",
                "printYear": 1981
            },
            {
                "title": "Atlas Shrugged",
                "description": "Atlas Shrugged is a 1957 novel by Ayn Rand. Rand's fourth and final novel, it was also her longest, and the one she considered to be her magnum opus in the realm of fiction writing. Atlas Shrugged includes elements of science fiction, mystery, and romance, and it contains Rand's most extensive statement of Objectivism in any of her works of fiction. The theme of Atlas Shrugged, as Rand described it, is \"the role of man's mind in existence\". The book explores a number of philosophical themes from which Rand would subsequently develop Objectivism. In doing so, it expresses the advocacy of reason, individualism, and capitalism, and depicts what Rand saw to be the failures of governmental coercion.",
                "author": "Ayn Rand",
                "isbn": "0-459-18937-3",
                "printYear": 1957
            },
            {
                "title": "Assassin's Creed: Renaissance",
                "description": "Assassin's Creed: Renaissance is a novel written by Oliver Bowden based on the game Assassin's Creed II. It was released in the United Kingdom on 26 November 2009 and in North America on 23 February 2010.",
                "author": "Oliver Bowden",
                "isbn": "9780141046303",
                "printYear": 2009
            }
        ]

## Find books by phrase
Endpoint: /find

Method: GET

    Request
        phrase: String
    Response: Message about result of request

## Get books by phrase
Endpoint: /find/{pageNumber}

Method: GET

    Request
        phrase: String
    Response: List of books by pagination