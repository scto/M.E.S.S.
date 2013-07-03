package assets;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetDescriptor;
import com.badlogic.gdx.assets.AssetLoaderParameters;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.assets.loaders.AsynchronousAssetLoader;
import com.badlogic.gdx.assets.loaders.FileHandleResolver;
import com.badlogic.gdx.graphics.g2d.ParticleEffect;
import com.badlogic.gdx.utils.Array;

public class ParticleEffectManager extends AsynchronousAssetLoader<ParticleEffect, ParticleEffectManager.ParticleEffectParameter>{

	public ParticleEffectManager(FileHandleResolver resolver) {
		super(resolver);
	}

	@Override
	public void loadAsync(AssetManager manager, String fileName,ParticleEffectManager.ParticleEffectParameter parameter) {
		new ParticleEffect();
	}

	@Override
	public ParticleEffect loadSync(AssetManager manager, String fileName, ParticleEffectManager.ParticleEffectParameter parameter) {
		ParticleEffect p = new ParticleEffect();
		p.load(Gdx.files.internal("particules/" + fileName), Gdx.files.internal("particules"));
		return p;
	}

	@Override
	public Array<AssetDescriptor> getDependencies(String fileName, ParticleEffectManager.ParticleEffectParameter parameter) {
		return null;
	}

    static public class ParticleEffectParameter extends AssetLoaderParameters<ParticleEffect> {
        public ParticleEffectParameter () {
        }
    }
}
