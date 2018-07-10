package cours.intechinfo.com.perenoeltp

enum class Style
{
    Rock, Metal, Hardcore
}

data class Musician(val name:String, val style : Style)
data class MatchResult(val style:Style, var occurence : Int)

/**
 * Created by Kévin Roussel on 10/07/2018.
 */

class MusicMatcher
{
    val favorite = mutableListOf<Musician>();
    val occurences = mutableListOf<MatchResult>(
            MatchResult(Style.Hardcore, 0),
            MatchResult(Style.Rock, 0),
            MatchResult(Style.Metal, 0)
    )


    fun GetAllMusicians() : List<Musician>
    {
        return mutableListOf<Musician>(
                Musician("Metallica", Style.Metal) ,
                Musician("Korn", Style.Metal),
                Musician("Gojira", Style.Metal),
                Musician("Nirvana", Style.Rock),
                Musician("ACDC", Style.Rock),
                Musician("Muse", Style.Rock),
                Musician("Hatebread", Style.Hardcore),
                Musician("Converge", Style.Hardcore),
                Musician("Isis", Style.Hardcore)
        )
    }

    fun AddFavorite(name: String) : MusicMatcher
    {
        if(favorite.find { it.name == name } != null) return this

        GetAllMusicians().firstOrNull { it.name == name }?.let { favorite.add(it); val ctx=it; occurences.first(){ it.style == ctx.style}.occurence ++; }

        return this;
    }

    fun GetSuggestions() : List<Musician>
    {
        val all = GetAllMusicians()
        val possibilities = all.filter { !favorite.contains(it) }
        occurences.sortBy { it.occurence }

        return possibilities.filter { it.style == occurences[0].style || it.style == occurences[1].style}
    }

}

