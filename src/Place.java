/**
 * 
 */

public class Place {

	private int _col;
	private int _lig;
	
	    
	///////////////////////////////////////////////////////////////////////////////////////////////
    //Constructeur
	///////////////////////////////////////////////////////////////////////////////////////////////
	/**
	 * @param _col
	 * @param _lig
	 */
	public Place(int _lig, int _col) {
		this._col = _col;
		this._lig = _lig;
	}
	
    
	///////////////////////////////////////////////////////////////////////////////////////////////
    //Accesseurs / modifieurs
	///////////////////////////////////////////////////////////////////////////////////////////////

	/**
	 * @return the _col
	 */
	public int get_col() {
		return _col;
	}

	/**
	 * @param _col the _col to set
	 */
	public void set_col(int _col) {
		this._col = _col;
	}
	/**
	 * @return the _lig
	 */
	public int get_lig() {
		return _lig;
	}
	/**
	 * @param _lig the _lig to set
	 */
	public void set_lig(int _lig) {
		this._lig = _lig;
	}
	
}
