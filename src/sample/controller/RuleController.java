package sample.controller;

import sample.model.Rule;

import java.util.List;

public class RuleController {
    private List<Rule> rules;

    public void addRule(String ruleName){
        if(!contains(ruleName)){
           rules.add(new Rule(ruleName));
        }
    }

    public List<Rule> getRules(){
        return rules;
    }

    public boolean contains(String ruleName){
        return rules.stream().anyMatch(x->x.name.equals(ruleName));
    }
}