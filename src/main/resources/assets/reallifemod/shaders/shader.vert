//vertex shader
#version 110

uniform float Eta;
uniform vec4 texture_scaling;
varying vec3 Reflect;
varying vec3 Refract;
varying vec3 i;
varying vec3 n;
varying vec4 original_color;

void main()
{
    vec4 ecPosition = gl_ModelViewMatrix * gl_Vertex;
    vec3 ecPosition3 = ecPosition.xyz;

  vec3 aux = vec3(gl_LightSource[0].position - ecPosition);
  vec3 binormal = vec3(gl_MultiTexCoord1.yzxy * gl_Normal.zxyz) - 
     vec3(gl_MultiTexCoord1.zxyz * gl_Normal.yzxy);
  vec3 temp = vec3(gl_MultiTexCoord1);
  mat3 TBN_Matrix = gl_NormalMatrix * mat3(temp, binormal, gl_Normal);
    gl_TexCoord[1].xyz = aux * TBN_Matrix; 
     gl_TexCoord[2].xyz = vec3(-ecPosition) *TBN_Matrix;

    i = normalize(ecPosition3);
    n = abs(normalize(gl_NormalMatrix * gl_Normal));
    gl_TexCoord[0] = texture_scaling * gl_TextureMatrix[0];
    Refract = refract(i, n, Eta);
    
    Reflect = reflect(i, n);
    original_color = gl_Color;
    gl_Position = ftransform();
}
