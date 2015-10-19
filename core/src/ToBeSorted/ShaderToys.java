package ToBeSorted;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.glutils.ShaderProgram;

/**
 * Created by julien on 19/10/15.
 */
public class ShaderToys {
    public ShaderProgram getShader(String fragment) {
        return new ShaderProgram(Gdx.files.internal("vertex.glsl").readString(), Gdx.files.internal(fragment).readString());
    }
}
