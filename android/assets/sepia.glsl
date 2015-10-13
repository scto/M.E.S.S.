#ifdef GL_ES
    precision mediump float;
#endif

varying vec4 v_color;
varying vec2 v_texCoords;
uniform sampler2D u_texture;
uniform mat4 u_projTrans;

void main() {
        vec4 color = texture2D(u_texture, v_texCoords).rgba;
        gl_FragColor = vec4(
	        color.r * .393 + color.g * .769 + color.b * .189,
	        color.r * .349 + color.g * .686 + color.b * .168,
	        color.r * .272 + color.g * .534 + color.b * .131,
	        color.a);
	        
	        //        //vec4 color = texture2D(u_texture, v_texCoords).rgba;
        //gl_FragColor = vec4(	        clamp(color.r + u_factor, 0, 1),	        clamp(color.g + u_factor, 0, 1),	        clamp(color.b + u_factor, 0, 1),	        color.a);
}         