#ifdef GL_ES
    precision mediump float;
#endif

varying vec4 v_color;
varying vec2 v_texCoords;
uniform sampler2D u_texture;
uniform float iGlobalTime;

const vec3 SEPIA = vec3(1.0, 0.4, 0.4); 

void main() {
	//uses NTSC conversion weights
	vec4 color = texture2D(u_texture, v_texCoords).rgba;
    float gray = (color.r + color.g + color.b) / 3.0;
    vec3 grayscale = vec3(gray);
    vec3 sepia = grayscale * SEPIA;
    gl_FragColor = vec4(mix(color.rgb, sepia, iGlobalTime), color.a);
}         