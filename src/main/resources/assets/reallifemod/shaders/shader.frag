//fragment shader
#version 110

uniform sampler2D texture0;
uniform float Eta;
uniform float FresnelPower;
varying vec3 Reflect;
varying vec3 Refract;
varying vec3 i;
varying vec3 n;
varying vec4 original_color;

void main()
{
    float F = ((1.0-Eta) * (1.0-Eta)) / ((1.0+Eta) *(1.0 +Eta));
    float Ratio = F + (1.0 -F) * pow((1.0 - dot(-i,n)), FresnelPower);
    vec3 Refract1 = vec3(gl_TexCoord[0] * vec4(Refract, 1.0));
    vec3 Reflect1 = vec3(gl_TexCoord[0] * vec4(Reflect, 1.0));
    vec3 refractColor = vec3(texture2D(texture0, vec2(Refract1)));
    vec3 reflectColor = vec3(texture2D(texture0, vec2(Reflect1)));
    vec3 color = mix(refractColor, reflectColor, Ratio);
  gl_FragColor = original_color * vec4(color, 1);
}
