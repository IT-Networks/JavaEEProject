package backend.enterpriseLogic;
/**
 * Klasse, die alle Fehlermeldungen aus dem Backend bündelt.
 */
public final class ErrorHandler {
	public static final String STARTORTNICHTGEFUNDEN = "Der Startort konnte nicht gefunden";
	public static final String ZIELORTNICHTGEFUNDEN = "Der Zielort konnte nicht gefunden";
	public static final String RELATIONSCHONVORHANDEN = "Die Relation ist schon vorhanden";
	public static final String MAHLZEITSCHONVORHANDEN = "Die Mahlzeit ist schon vorhanden";
	public static final String RELATIONNICHTVORHANDEN = "Die Relation ist nicht vorhanden";
	public static final String NUTZERSCHONVORHANDEN = "Dieser Nutzer ist schon vorhanden";
	public static final String DATENUNVOLLSTAENDIG = "Bitte geben Sie alle Information an!";
	public static final String NUTZERNICHTGEFUNDEN = "Unter dem angebenen Nutzernamen konnte kein Nutzer gefunden werden!";
	public static final String NUTZERPASSWORTFALSCH = "Bitte geben Sie das richtige Passwort an!";
	public static final String NUTZERTYPNICHTVORHANDEN = "Dieser Nutzertyp ist nicht vorhanden!";
	public static final String FLUGZEUGNICHTZUGEORDNET = "Dieser Flug hat noch kein zugeordnetes Flugzeug!";
	public static final String FLUGZEUGBEREITSZUGEORDNET = "Dieses Flugzeug wurde bereits einem Flug zugeordnet.";
	public static final String FLUGZEUGDIESEMFLUGZUGEORDNET = "Dieser Flugzeug wurde bereits diesem Flug zugeordnet.";
	public static final String STATUSNICHTSETZBAR = "Der Status kann nicht gesetzt werden.";
	public static final String BUCHUNGSCHONVORHANDEN = "Diese Buchung ist bereits vorhanden.";
}
