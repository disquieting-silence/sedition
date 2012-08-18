package dsq.sedition.screens;

import android.app.Activity;
import android.content.Intent;
import android.opengl.GLSurfaceView;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.*;
import dsq.sedition.R;
import dsq.sedition.core.DefaultGame;
import dsq.sedition.core.Level;
import dsq.sedition.event.DefaultEventListener;
import dsq.sedition.event.EventListener;
import dsq.sedition.gl.GLRenderer;
import dsq.sedition.maze.level.Level1;
import dsq.sedition.options.Difficulty;
import dsq.sedition.options.Options;
import dsq.sedition.ui.DefaultGameUi;
import dsq.sedition.ui.GameUi;
import dsq.sedition.core.Game;
import dsq.sedition.util.None;
import dsq.sedition.util.Option;
import dsq.sedition.util.Some;

/* This project has been inspired by: http://www.javacodegeeks.com/2011/09/android-game-development-switching-from.html */
public class Main extends Activity {
    
    private Option<Difficulty> difficultyOption = new None<Difficulty>();

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.options);

        final Spinner difficulty = getDifficultyUi();
        final ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.game_difficulty_options, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        difficulty.setAdapter(adapter);

        difficulty.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(final AdapterView<?> adapterView, final View view, final int i, final long l) {
                final int index = adapterView.getSelectedItemPosition();
                final Difficulty[] values = Difficulty.values();
                difficultyOption = new Some<Difficulty>(values[index % values.length]);
            }

            @Override
            public void onNothingSelected(final AdapterView<?> adapterView) {
                difficultyOption = new None<Difficulty>();
            }
            
        });

        final Button cancelButton = (Button)findViewById(R.id.cancel);
        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {
                setResult(RESULT_CANCELED);
                finish();
            }
        });

        final Button confirmButton = (Button) findViewById(R.id.confirm);
        confirmButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {
                // FIX 18/08/12 Surely this is wrong.
                final Difficulty difficulty = difficultyOption.getOr(Difficulty.MEDIUM);
                final Intent intent = new Intent(view.getContext(), GameScreen.class);
                intent.putExtra(Options.KEY, new Options(difficulty));
                startActivity(intent);
            }
        });
    }

    private Spinner getDifficultyUi() {
        return (Spinner) findViewById(R.id.difficulty_spinner);
    }
}


/*
protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ersatz_setting);

        db = lifecycle.open(this);

        setupCooloff();
        setupCancel();
        setupConfirm();
    }

    private void setupCooloff() {
        // FIX 26/01/12 http://developer.android.com/resources/tutorials/views/hello-spinner.html
        Spinner spinner = (Spinner) findViewById(R.id.cooloff_spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
                this, R.array.settings_ui_cooloff_options, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        long cooloff = util.getCooloff(db);
        Log.v("ERSATZ", "cooloff: " + cooloff);
        int index = adapter.getPosition(String.valueOf(cooloff));
        Log.v("ERSATZ", "value: " + index);
        spinner.setSelection(index);
    }
    */