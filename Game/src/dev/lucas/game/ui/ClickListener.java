package dev.lucas.game.ui;

/** 
 * <i><b>ClickListener</b></i>
 * <pre> public interface ClickListener</pre>
 * <p>This class defines all the necessary methods and variables for a ClickListener.</p>
 * @see {@link dev.lucas.game.ui.UIObject UIObject} , {@link dev.lucas.game.ui.UIManager UIManager}
 * **/
public interface ClickListener {
	// An interface that has an empty onClick method
	// This is meant for other UI Objects to be able to interacted with.
	/**
	 * <i><b>onClick</b></i>
	 * <pre>	public void onClick()</pre>
	 * <p>This method is an empty method. This method is supposed to be overridden by UIObjects.</p>
	 * @param None
	 * @return void
	 * @see {@link dev.lucas.game.ui.UIObject UIObject} , {@link dev.lucas.game.ui.UIManager UIManager}
	 * **/
	public void onClick();
}
