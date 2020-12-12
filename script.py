s = """rule "RULENAME"
when
	not Answer(answer == AnswerNames.YES_XXX);
	not Answer(answer == AnswerNames.NO_XXX);
	Answer(answer == AnswerNames.YYY); 
then
	System.out.println("XXX");
	ArrayList<Answer> answers_tmp = new ArrayList<Answer>(asList(new Answer(AnswerNames.YES_XXX),new Answer(AnswerNames.NO_XXX)));
	
	Question q = new Question("RULENAME?", answers_tmp);
	insert(q);
end
"""
ss = "YES_XXX(\"Yes\"), NO_XXX(\"No\"), "
def mod(rulename,xxx,yyy):
    p = s.replace("XXX",xxx)
    p = p.replace("YYY",yyy)
    p = p.replace("RULENAME",rulename)
    return p


#print(mod("Can you dance","DANCE","SING"))
f = open("output.txt", "w")
ff = open("output2.txt", "w")
for line in open("input.txt"):
    split=line.split(";")
    #rulename, rule enum, previous rule
    output = mod(split[0], split[1], split[2])
    f.write(output)
    ff.write(ss.replace("XXX", split[1]))
f.close()
ff.close()
