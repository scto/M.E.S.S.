package shaders;

import com.badlogic.gdx.graphics.Mesh;
import com.badlogic.gdx.graphics.VertexAttribute;
import com.badlogic.gdx.graphics.VertexAttributes.Usage;
import com.badlogic.gdx.graphics.glutils.ShaderProgram;
import com.badlogic.gdx.math.Matrix4;

public final class Ripple {
	
	static String vertexShader = 
			"attribute vec4 a_position;    \n"
			+ "attribute vec2 a_texCoord0;\n"
			+ "uniform mat4 u_worldView;\n"
			+ "varying vec4 v_color;" 
			+ "varying vec2 v_texCoords;"
			+ "void main()                  \n"
			+ "{                            \n"
			+ "   v_color = vec4(1, 1, 1, 1); \n"
			+ "   v_texCoords = a_texCoord0; \n"
			+ "   gl_Position =  u_worldView * a_position;  \n"
			+ "}                            \n";
	
	static String fragmentShader = "#ifdef GL_ES\n"
			+ "precision mediump float;\n"
			+ "#endif\n"
			+ "varying vec4 v_color;\n"
			+ "varying vec2 v_texCoords;\n"
			+ "uniform sampler2D u_texture;\n"
			+ "uniform sampler2D u_texture2;\n"
			+ "uniform float timedelta;\n"
			+ "void main()                                  \n"
			+ "{                                            \n"
			+ "  vec2 displacement = texture2D(u_texture2, v_texCoords/6.0).xy;\n" //
			+ "  float t=v_texCoords.y +displacement.y*0.1-0.15+  (sin(v_texCoords.x * 60.0+timedelta) * 0.005); \n" //
			+ "  gl_FragColor = v_color * texture2D(u_texture, vec2(v_texCoords.x,t));\n"
			+ "}";

	static String fragmentShader2 = "#ifdef GL_ES\n" 
		    + "precision mediump float;\n"
			+ "#endif\n" 
			+ "varying vec4 v_color;\n"
			+ "varying vec2 v_texCoords;\n" 
			+ "uniform sampler2D u_texture;\n"
			+ "void main()                                  \n"
			+ "{                                            \n"
			+ "  gl_FragColor = v_color * texture2D(u_texture, v_texCoords);\n"
			+ "}";


	static ShaderProgram shader;
	static ShaderProgram waterShader;
	static Matrix4 matrix;
	static Mesh waterMesh;
	
	public static ShaderProgram init() {
		// important since we aren't using some uniforms and attributes that SpriteBatch expects
		ShaderProgram.pedantic = false;
		
		shader = new ShaderProgram(vertexShader, fragmentShader);
		
//		getWaterShader(matrix);
		
		return shader;
	}

	public static ShaderProgram getWaterShader(Matrix4 combined) {
		ShaderProgram.pedantic = false;
		waterShader = new ShaderProgram(vertexShader, fragmentShader2);
		waterShader.setUniformMatrix("u_projTrans", combined);
		waterMesh = createQuad(-1, -1, 1, -1, 1, -0.3f, -1, -0.3f);
		return waterShader;
	}
	
	public static Mesh createQuad(float x1, float y1, float x2, float y2, float x3,
			float y3, float x4, float y4) {
		float[] verts = new float[20];
		int i = 0;

		verts[i++] = x1; // x1
		verts[i++] = y1; // y1
		verts[i++] = 0;
		verts[i++] = 1f; // u1
		verts[i++] = 1f; // v1

		verts[i++] = x2; // x2
		verts[i++] = y2; // y2
		verts[i++] = 0;
		verts[i++] = 0f; // u2
		verts[i++] = 1f; // v2

		verts[i++] = x3; // x3
		verts[i++] = y3; // y2
		verts[i++] = 0;
		verts[i++] = 0f; // u3
		verts[i++] = 0f; // v3

		verts[i++] = x4; // x4
		verts[i++] = y4; // y4
		verts[i++] = 0;
		verts[i++] = 1f; // u4
		verts[i++] = 0f; // v4

		Mesh mesh = new Mesh(true, 4, 0, // static mesh with 4 vertices and no
											// indices
				new VertexAttribute(Usage.Position, 3,
						ShaderProgram.POSITION_ATTRIBUTE), new VertexAttribute(
						Usage.TextureCoordinates, 2,
						ShaderProgram.TEXCOORD_ATTRIBUTE + "0"));

		mesh.setVertices(verts);
		return mesh;

	}
}
