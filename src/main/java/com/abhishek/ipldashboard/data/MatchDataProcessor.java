
package com.abhishek.ipldashboard.data;

import com.abhishek.ipldashboard.model.Match;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.batch.item.ItemProcessor;

import java.time.LocalDate;

public class MatchDataProcessor implements ItemProcessor<MatchInput, Match> {

    private static final Logger log = LoggerFactory.getLogger(MatchDataProcessor.class);

    @Override
    public Match process(final MatchInput matchInput) throws Exception {
        Match match = new Match();
        match.setId(Long.parseLong(matchInput.getId()));
        match.setCity(matchInput.getCity());
        match.setDate(LocalDate.parse(matchInput.getDate()));
        match.setPlayerOfMatch(matchInput.getPlayer_of_match());
        match.setVenue(matchInput.getVenue());

        if (matchInput.getTeam1().equals(matchInput.getWinner())) {
            if ("field".equals(matchInput.getToss_decision())) {
                match.setTeam1(matchInput.getTeam2());
                match.setTeam2(matchInput.getTeam1());
            }
            else {
                match.setTeam1(matchInput.getTeam1());
                match.setTeam2(matchInput.getTeam2());
            }
        }
        else {
            if ("field".equals(matchInput.getToss_decision())) {
                match.setTeam1(matchInput.getTeam1());
                match.setTeam2(matchInput.getTeam2());
            }
            else {
                match.setTeam1(matchInput.getTeam2());
                match.setTeam2(matchInput.getTeam1());
            }
        }

        match.setTossWinner(matchInput.getToss_winner());
        match.setTossDecision(matchInput.getToss_decision());
        match.setResult(matchInput.getResult());
        match.setResultMargin(matchInput.getResult_margin());
        match.setUmpire1(matchInput.getUmpire1());
        match.setUmpire2(matchInput.getUmpire2());


        return match;
    }

}

